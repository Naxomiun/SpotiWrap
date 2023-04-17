package com.wachon.spotiwrap.core.persistence.encrypted

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPreferencesProvider(
    context: Context
) : EncryptedDataProvider {

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

    override fun getEncryptedString(key: EncryptedItem): String? {
        return sharedPreferences.getString(key.name.lowercase(), null)
    }

    override fun setAuthScopes(key: EncryptedItem, value: List<String>) {
        sharedPreferences.edit().putString(
            key.name.lowercase(),
            Gson().toJson(value)
        ).apply()
    }

    override fun getAuthScopes(key: EncryptedItem): List<String> {
        return Gson().fromJson(
            sharedPreferences.getString(key.name.lowercase(), "[]"),
            object : TypeToken<List<String>>() {}.type
        )
    }

}