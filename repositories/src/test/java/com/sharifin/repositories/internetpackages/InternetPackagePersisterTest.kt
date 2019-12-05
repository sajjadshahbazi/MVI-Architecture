package com.sharifin.repositories.internetpackages

import com.sharifin.databaserepo.entities.InternetPackageCacheEntity
import com.sharifin.databaserepo.entities.InternetPackageEntity
import com.sharifin.databaserepo.entities.MyObjectBox
import com.sharifin.repositories.internetpackages.mapper.InternetPackageEntityServerMapper
import com.sharifin.repositories.internetpackages.mapper.InternetPackageServerEntityMapper
import com.sharifin.servermodel.ArrayListMoshiAdapter
import com.sharifin.servermodel.ServerModelsJsonAdapterFactory
import com.sharifin.servermodel.internetpackages.InternetPackageServerModel
import com.sharifin.testhelper.rule.RxTestSchedulerRule
import com.sharifin.testhelper.rule.TEST_SCHEDULER_INSTANCE
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.objectbox.BoxStore
import io.objectbox.DebugFlags
import io.objectbox.kotlin.boxFor
import org.assertj.core.api.Assertions.assertThat
import org.intellij.lang.annotations.Language
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.File

class InternetPackagePersisterTest {

    private val serverEntityMapper = InternetPackageServerEntityMapper()
    private val entityServerMapper = InternetPackageEntityServerMapper()

    private lateinit var boxStoreDir: File
    private var store: BoxStore? = null

    lateinit var persister: InternetPackagePersister

    private val moshi = Moshi
            .Builder()
            .add(ServerModelsJsonAdapterFactory.INSTANCE)
            .add(ArrayListMoshiAdapter.FACTORY)
            .build()

    @get:Rule
    val schedulerRule = RxTestSchedulerRule()

    @Before
    fun setUp() {
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

        persister = InternetPackagePersister(store!!, serverEntityMapper, entityServerMapper)

    }

    @After
    fun tearDown() {
        if (store != null) {
            store!!.close()
            store!!.deleteAllFiles()
        }
    }

    @Test
    fun `persister stores packages correctly`() {
        @Language("JSON")
        val packagesJson = "[\n  {\n    \"id\" : 1\n  },\n  {\n    \"id\" : 2\n  },\n  {\n    \"id\" : 3\n  },\n  {\n    \"id\" : 4\n  },\n  {\n    \"id\" : 5\n  }\n]"

        val jsonType = Types.newParameterizedType(
                ArrayList::class.java,
                InternetPackageServerModel::class.java)

        val packages = moshi
                .adapter<ArrayList<InternetPackageServerModel>>(jsonType)
                .fromJson(packagesJson)!!

        val testObserver = persister
                .persist(packages)
                .test()

        TEST_SCHEDULER_INSTANCE.triggerActions()

        testObserver.assertComplete()

        val packageStore = store!!.boxFor<InternetPackageEntity>()

        assertThat(packageStore.all)
                .hasSize(packages.size)
                .containsExactlyInAnyOrder(
                        *(packages
                                .map {
                                    serverEntityMapper.map(it)
                                }
                                .toTypedArray())
                )
    }

    @Test
    fun `persister stores and retrieves packages correctly`() {
        @Language("JSON")
        val packagesJson = "[\n  {\n    \"id\" : 1\n  },\n  {\n    \"id\" : 2\n  },\n  {\n    \"id\" : 3\n  },\n  {\n    \"id\" : 4\n  },\n  {\n    \"id\" : 5\n  }\n]"

        val jsonType = Types.newParameterizedType(
                ArrayList::class.java,
                InternetPackageServerModel::class.java)

        val packages = moshi
                .adapter<ArrayList<InternetPackageServerModel>>(jsonType)
                .fromJson(packagesJson)!!

        val testObserver = persister
                .persist(packages)
                .test()

        TEST_SCHEDULER_INSTANCE.triggerActions()

        testObserver.assertComplete()

        val retrievedPackages = persister.getOnce().blockingGet()

        assertThat(
                retrievedPackages
        ).hasSize(
                packages.size
        ).containsExactlyInAnyOrderElementsOf(
                packages
        )
    }

    @Test
    fun `persister retrieves packages correctly`() {
        @Language("JSON")
        val packagesJson = "[\n  {\n    \"id\" : 1\n  },\n  {\n    \"id\" : 2\n  },\n  {\n    \"id\" : 3\n  },\n  {\n    \"id\" : 4\n  },\n  {\n    \"id\" : 5\n  }\n]"

        val jsonType = Types.newParameterizedType(
                ArrayList::class.java,
                InternetPackageServerModel::class.java)

        val packages = moshi
                .adapter<ArrayList<InternetPackageServerModel>>(jsonType)
                .fromJson(packagesJson)!!

        val box = store!!.boxFor<InternetPackageEntity>()
        box.put(packages.map { serverEntityMapper.map(it) })

        val cacheBox = store!!.boxFor<InternetPackageCacheEntity>()
        val cacheEntity = InternetPackageCacheEntity(ID_INTERNET_PACKAGE_CACHE)
        cacheBox.attach(cacheEntity)
        cacheEntity.packages.addAll(packages.map { serverEntityMapper.map(it) })
        cacheEntity.packages.applyChangesToDb()
        cacheBox.put(cacheEntity)

        val testObserver = persister
                .getOnce()
                .test()

        testObserver.awaitCount(1)

        TEST_SCHEDULER_INSTANCE.triggerActions()

        val retrievedPackages = testObserver.values()[0]

        testObserver.assertComplete()

        assertThat(
                retrievedPackages
        ).hasSize(
                packages.size
        ).containsExactlyInAnyOrderElementsOf(
                packages
        )
    }
}