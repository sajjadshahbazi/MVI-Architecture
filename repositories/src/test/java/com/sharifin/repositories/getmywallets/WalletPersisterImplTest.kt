package com.sharifin.repositories.getmywallets

import com.sharifin.base.sharedprefs.SharedPrefs
import com.sharifin.databaserepo.entities.MyObjectBox
import com.sharifin.databaserepo.entities.WalletEntity
import com.sharifin.repositories.cache.AppCacheCryptor
import com.sharifin.servermodel.ServerModelsJsonAdapterFactory
import com.sharifin.servermodel.WalletServerModel
import com.sharifin.testhelper.factories.randomString
import com.sharifin.testhelper.rule.RxTrampolineSchedulerRule
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import io.objectbox.BoxStore
import io.objectbox.DebugFlags
import io.objectbox.kotlin.boxFor
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.File

class WalletPersisterTest {
    private lateinit var boxStoreDir: File
    private var store: BoxStore? = null

    private val fakePrefs = mockk<SharedPrefs>()
    private val moshi = Moshi.Builder().add(ServerModelsJsonAdapterFactory.INSTANCE).build()
    private val cryptor = mockk<AppCacheCryptor>()
    private lateinit var persister: WalletPersisterImpl

    @get:Rule
    val schedulerRule = RxTrampolineSchedulerRule()

    @Before
    fun setUp() {
        clearMocks(cryptor, fakePrefs)
        // store the database in the systems temporary files folder
        val tempFile = File.createTempFile("object-store-test", "")
        // ensure file does not exist so builder creates a directory instead
        tempFile.delete()
        boxStoreDir = tempFile
        store = MyObjectBox.builder()
                // add directory flag to change where ObjectBox puts its database files
                .directory(boxStoreDir)
                // optional: add debug flags for more detailed ObjectBox log output
                .debugFlags(DebugFlags.LOG_QUERIES or DebugFlags.LOG_QUERY_PARAMETERS)
                .build()


        persister = WalletPersisterImpl(store!!)
        persister.prefs = fakePrefs
        persister.moshi = moshi
        persister.cryptor = cryptor
    }

    @After
    fun tearDown() {
        if (store != null) {
            store!!.close()
            store!!.deleteAllFiles()
        }
    }

    @Test
    fun `correctly saves data in db`() {
        val phone = randomString()

        every {
            fakePrefs.getPhone()
        } returns Single.just(phone)

        val type = Types.newParameterizedType(List::class.java, WalletServerModel::class.java)
        val walletMoshi = moshi.adapter<List<WalletServerModel>>(type)

        //language=JSON
        val walletJson = "[\n  {\n    \n  },\n  {\n    \n  },\n  {\n    \n  },\n  {\n    \n  },\n  {\n    \n  }\n]"

        val list = walletMoshi.fromJson(walletJson)!!

        every {
            cryptor.encrypt(any(), any())
        } returns walletJson

        val testObserver = persister.persist(list)
                .test()

        testObserver.assertComplete()

        val walletBox = store!!.boxFor<WalletEntity>()
        val allWallets = walletBox.all

        assertThat(allWallets)
                .first()
                .isEqualToIgnoringGivenFields(
                        WalletEntity(wallets = walletJson, phone = phone),
                        "cacheTime",
                        "id")
    }

    @Test
    fun `correctly retrieves data from db`() {
        val phone = randomString()

        every {
            fakePrefs.getPhone()
        } returns Single.just(phone)

        val type = Types.newParameterizedType(List::class.java, WalletServerModel::class.java)
        val walletMoshi = moshi.adapter<List<WalletServerModel>>(type)

        //language=JSON
        val walletJson = "[\n  {\n    \n  },\n  {\n    \n  },\n  {\n    \n  },\n  {\n    \n  },\n  {\n    \n  }\n]"

        val list = walletMoshi.fromJson(walletJson)!!

        val walletBox = store!!.boxFor<WalletEntity>()
        walletBox.put(WalletEntity(phone = phone, wallets = walletJson))

        every {
            cryptor.decrypt(any(), any())
        } returns walletJson

        val testObserver = persister.observe()
                .test()

        testObserver.assertNotComplete()

        val models = testObserver
                .awaitCount(1)
                .values()[0]

        assertThat(models)
                .isEqualTo(list)
    }

    @Test
    fun `correctly saves and retrieves data from db`() {
        val phone = randomString()

        val type = Types.newParameterizedType(List::class.java, WalletServerModel::class.java)
        val walletMoshi = moshi.adapter<List<WalletServerModel>>(type)

        //language=JSON
        val walletJson = "[\n  {\n    \n  },\n  {\n    \n  },\n  {\n    \n  },\n  {\n    \n  },\n  {\n    \n  }\n]"

        val list = walletMoshi.fromJson(walletJson)!!

        every {
            fakePrefs.getPhone()
        } returns Single.just(phone)

        every {
            cryptor.decrypt(any(), any())
        } returns walletJson

        every {
            cryptor.encrypt(any(), any())
        } returns walletJson

        persister.persist(list)
                .test()
                .assertComplete()

        val testObserver = persister.observe()
                .test()

        testObserver.assertNotComplete()

        val models = testObserver
                .awaitCount(1)
                .values()[0]

        assertThat(models)
                .isEqualTo(list)
    }
}