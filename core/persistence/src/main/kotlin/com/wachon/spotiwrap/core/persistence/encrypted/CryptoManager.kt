package com.wachon.spotiwrap.core.persistence.encrypted

import android.security.keystore.KeyProperties
import android.util.Base64
import java.nio.charset.Charset
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class CryptoManager {

    private val ivSpec = IvParameterSpec(VECTOR.toByteArray(Charset.forName("UTF-8")))
    private val skeySpec = SecretKeySpec(KEY.toByteArray(Charset.forName("UTF-8")), ALGORITHM)

    private val encryptCipher
        get() = Cipher.getInstance(TRANSFORMATION).apply {
            init(Cipher.ENCRYPT_MODE, skeySpec, ivSpec)
        }

    private val decryptCypher
        get() =  Cipher.getInstance(TRANSFORMATION).apply {
            init(Cipher.DECRYPT_MODE, skeySpec, ivSpec)
        }

    fun encrypt(value: String): String {
        val encryptedBytes = encryptCipher.doFinal(value.toByteArray())
        return Base64.encodeToString(encryptedBytes, Base64.NO_WRAP)
    }

    fun decrypt(value: String): String {
        val values: ByteArray = Base64.decode(value, Base64.NO_WRAP)
        return String(decryptCypher.doFinal(values))
    }

    companion object {
        private const val VECTOR = "encryptionvector"
        private const val KEY = "aesEncryptionKey"
        private const val ALGORITHM = KeyProperties.KEY_ALGORITHM_AES
        private const val BLOCK_MODE = KeyProperties.BLOCK_MODE_CBC
        private const val PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7
        private const val TRANSFORMATION = "$ALGORITHM/$BLOCK_MODE/$PADDING"
    }

}