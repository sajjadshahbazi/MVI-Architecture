@file:Suppress("DEPRECATION")

package com.sharifin.repositories.cache

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.security.KeyPairGeneratorSpec
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import okio.ByteString.Companion.decodeBase64
import okio.ByteString.Companion.toByteString
import java.io.ByteArrayOutputStream
import java.math.BigInteger
import java.security.Key
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.SecureRandom
import java.util.*
import javax.crypto.Cipher
import javax.crypto.CipherOutputStream
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec
import javax.security.auth.x500.X500Principal

const val TRANSFORMATION_RSA = "RSA/ECB/NoPadding"
const val ANDROID_KEY_STORE = "AndroidKeyStore"
const val ANDROID_OPEN_SSL = "AndroidOpenSSL"
const val AES_ALGORITHM = "AES"
const val KEY_ALIAS_AES = "cache_aes_key"
const val KEY_ALIAS_RSA = "cache_rsa_key"

interface CacheCipherHandler {
    fun getCipher(phone: String, operationMode: Int): Cipher

    fun getKey(phone: String): Key

    fun generateKey(phone: String)

    fun removeKey(phone: String)

    companion object {
        fun getInstance(context: Context) =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    CacheCipherHandlerMImpl()
                } else {
                    CacheCipherHandlerKitKatImpl(context)
                }
    }
}

@TargetApi(Build.VERSION_CODES.M)
class CacheCipherHandlerMImpl : CacheCipherHandler {
    private val TRANSFORMATION_AES = "AES/GCM/NoPadding"

    override fun getCipher(phone: String, operationMode: Int): Cipher {
        val c = Cipher.getInstance(TRANSFORMATION_AES)
        c.init(operationMode, getKey(phone), GCMParameterSpec(128, ByteArray(12)))
        return c
    }

    override fun getKey(phone: String): Key {
        val keyStore = KeyStore.getInstance(ANDROID_KEY_STORE)
        keyStore.load(null)
        if (!keyStore.isKeyEntry(KEY_ALIAS_AES)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                generateKey(phone)
            }
        }
        return keyStore.getKey(KEY_ALIAS_AES, null) as SecretKey
    }

    override fun generateKey(phone: String) {
        val generator = KeyGenerator.getInstance(
                KeyProperties.KEY_ALGORITHM_AES,
                ANDROID_KEY_STORE)
        val spec = KeyGenParameterSpec.Builder(
                KEY_ALIAS_AES,
                KeyProperties.PURPOSE_ENCRYPT or
                        KeyProperties.PURPOSE_DECRYPT)
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .setRandomizedEncryptionRequired(false)
                .build()

        generator.init(spec)

        generator.generateKey()
    }

    override fun removeKey(phone: String) {
        val keyStore = KeyStore.getInstance(ANDROID_KEY_STORE)
        keyStore.load(null)
        keyStore.deleteEntry(KEY_ALIAS_AES)
    }
}

class CacheCipherHandlerKitKatImpl(
        private val context: Context
) : CacheCipherHandler, AESSharedPrefExt {
    private val TRANSFORMATION_AES = "AES/ECB/PKCS7Padding"

    @SuppressLint("GetInstance")
    override fun getCipher(phone: String, operationMode: Int): Cipher =
            Cipher.getInstance(TRANSFORMATION_AES, "BC")
                    .also {
                        it.init(operationMode, getKey(phone))
                    }

    override fun getKey(phone: String): Key {
        var encryptedAESKey = context getAESKey phone

        if (encryptedAESKey.isBlank()) {
            generateKey(phone)
        }

        encryptedAESKey = context getAESKey phone

        val key = encryptDecryptAES(Cipher.DECRYPT_MODE, encryptedAESKey.decodeBase64()!!.toByteArray())

        return SecretKeySpec(key, AES_ALGORITHM)
    }

    override fun generateKey(phone: String) {
        //creating a AES key
        val key = ByteArray(16)
        val secureRandom = SecureRandom()
        secureRandom.nextBytes(key)

        //encrypting AES with RSA
        val encryptedKey = encryptDecryptAES(Cipher.ENCRYPT_MODE, key)

        //storing aes key in shared preferences
        context.putAESKey(phone, encryptedKey.toByteString().base64())
    }

    @Suppress("DEPRECATION")
    private fun generateRSAKey() {
        val start = Calendar.getInstance()
        val end = Calendar.getInstance()
        end.add(Calendar.YEAR, 30)

        val spec = KeyPairGeneratorSpec.Builder(context)
                .setAlias(KEY_ALIAS_RSA)
                .setSubject(X500Principal("CN=$KEY_ALIAS_RSA"))
                .setSerialNumber(BigInteger.TEN)
                .setStartDate(start.time)
                .setEndDate(end.time)
                .build()
        val kpg = KeyPairGenerator.getInstance("RSA", ANDROID_KEY_STORE)
        kpg.initialize(spec)
        kpg.generateKeyPair()
    }

    private fun encryptDecryptAES(operationMode: Int, key: ByteArray): ByteArray {
        val keyStore = KeyStore.getInstance(ANDROID_KEY_STORE)
        keyStore.load(null)

        if (!keyStore.isKeyEntry(KEY_ALIAS_RSA)) {
            generateRSAKey()
        }
        val privateKeyEntry = keyStore.getEntry(KEY_ALIAS_RSA, null) as KeyStore.PrivateKeyEntry
        // Encrypt the text
        val inputCipher = Cipher.getInstance(TRANSFORMATION_RSA, ANDROID_OPEN_SSL)
        if (operationMode == Cipher.ENCRYPT_MODE) {
            inputCipher.init(operationMode, privateKeyEntry.certificate.publicKey)
        } else if (operationMode == Cipher.DECRYPT_MODE) {
            inputCipher.init(operationMode, privateKeyEntry.privateKey)
        }


        val outputStream = ByteArrayOutputStream()
        CipherOutputStream(outputStream, inputCipher).use {
            it.write(key)
        }
        return key
    }

    override fun removeKey(phone: String) {
        val keyStore = KeyStore.getInstance(ANDROID_KEY_STORE)
        keyStore.load(null)
        keyStore.deleteEntry(KEY_ALIAS_RSA)

        context.removeAESKey(phone)
    }

}