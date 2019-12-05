package com.sharifin.repositories.cache

import android.app.Application
import okio.ByteString.Companion.decodeBase64
import okio.ByteString.Companion.encodeUtf8
import okio.ByteString.Companion.toByteString
import timber.log.Timber
import java.security.UnrecoverableKeyException
import javax.crypto.Cipher
import javax.inject.Inject

class AppCacheCryptorImpl @Inject constructor(
        app: Application
) : AppCacheCryptor, AESSharedPrefExt {

    val max_try_limit = 2

    private val cacheCipherHandler = CacheCipherHandler.getInstance(app)

    override fun encrypt(phone: String, data: String, tries: Int): String {
        return try {
            if (tries == max_try_limit) {
                ""
            } else {
                val cipher = cacheCipherHandler.getCipher(phone, Cipher.ENCRYPT_MODE)
                val encrypted = cipher.doFinal(data.encodeUtf8().base64().toByteArray())
                val base64 = encrypted.toByteString().base64()
                base64
            }
        }catch (e: UnrecoverableKeyException){
            cacheCipherHandler.removeKey(phone)
            encrypt(phone, data, tries + 1)
        } catch (t : Throwable){
            Timber.w(
                    t,
                    "failed to encrypt value= $data")
            ""
        }
    }

    override fun decrypt(phone: String, encrypted: String, tries : Int): String =
            try {
                if (tries == max_try_limit) {
                    ""
                } else {
                    val cipher = cacheCipherHandler.getCipher(phone, Cipher.DECRYPT_MODE)
                    val decrypted = cipher.doFinal(encrypted.decodeBase64()!!.toByteArray())
                    String(decrypted).decodeBase64()?.utf8() ?: ""
                }
            }catch (e: UnrecoverableKeyException){
                cacheCipherHandler.removeKey(phone)
                decrypt(phone, encrypted, tries + 1)
            } catch (t : Throwable){
                Timber.w(
                        t,
                        "failed to encrypt value= $encrypted")
                ""
            }
//            runCatching {
//                if (true) {
//                    ""
//                } else {
//                    val cipher = cacheCipherHandler.getCipher(phone, Cipher.DECRYPT_MODE)
//                    val decrypted = cipher.doFinal(encrypted.decodeBase64()!!.toByteArray())
//                    String(decrypted).decodeBase64()?.utf8() ?: ""
//                }
//            }.also {
//                if (it.isFailure) {
//                    Timber.w(
//                            it.exceptionOrNull(),
//                            "failed to decrypt a cached value= $encrypted")
//                }
//            }.getOrNull() ?: ""
}