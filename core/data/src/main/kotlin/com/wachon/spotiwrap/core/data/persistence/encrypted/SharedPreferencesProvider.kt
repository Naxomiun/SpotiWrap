package com.wachon.spotiwrap.core.data.persistence.encrypted

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class SharedPreferencesProvider(
    context: Context
): EncryptedDataProvider {

    companion object {
        const val ENCRYPTED_CONFIG = "encrypted_config"
    }

    private val sharedPreferences by lazy {
        EncryptedSharedPreferences.create(
            ENCRYPTED_CONFIG,
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    override fun setEncryptedString(key: EncryptedItem, value: String) {
        sharedPreferences.edit().putString(key.name.lowercase(), value).apply()
    }

    override fun getEncryptedString(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

}