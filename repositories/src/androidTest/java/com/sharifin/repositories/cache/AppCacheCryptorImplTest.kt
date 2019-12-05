@file:Suppress("DEPRECATION")

package com.sharifin.repositories.cache

import android.content.Context
import android.preference.PreferenceManager
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnit4
import com.sharifin.testhelper.factories.randomString
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.security.KeyStore

@RunWith(AndroidJUnit4::class)
class AppCacheCryptorImplTest {

    lateinit var cryptor: AppCacheCryptorImpl

    @Before
    fun setup() {
        cryptor = AppCacheCryptorImpl(ApplicationProvider.getApplicationContext())

        clearSharedPrefs()
        clearingKeyStore()
    }

    @After
    fun tearDown() {
        clearSharedPrefs()
        clearingKeyStore()
    }

    @Test
    fun correctlyEncryptsDataMulti() {
        val map = HashMap<String, String>()

        (1..10000).forEach {
            map[randomString()] = randomString()
        }

        map.forEach { (phone, data) ->
            val encrypted = cryptor.encrypt(phone, data)
            assertThat(cryptor.decrypt(phone, encrypted)).isEqualTo(data)
        }
    }

    @Test
    fun correctlyEncryptsDataSingle() {
        val phone = randomString()
        val data = randomString()

        val encrypted = cryptor.encrypt(phone, data)
        assertThat(cryptor.decrypt(phone, encrypted)).isEqualTo(data)
    }

    private fun clearSharedPrefs() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        PreferenceManager.getDefaultSharedPreferences(context).edit().clear().commit()
    }

    private fun clearingKeyStore() {
        val keyStore = KeyStore.getInstance("AndroidKeyStore")
        keyStore.load(null)
        keyStore.aliases().toList().forEach {
            keyStore.deleteEntry(it)
        }
    }
}