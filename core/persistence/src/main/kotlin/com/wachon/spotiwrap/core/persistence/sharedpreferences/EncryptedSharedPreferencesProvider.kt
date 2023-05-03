package com.wachon.spotiwrap.core.persistence.sharedpreferences

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class SharedPreferencesProvider(
    context: Context
) : DataProvider {

    companion object {
        const val SHARED_CONFIG = "shared_config"
    }

    private val sharedPreferences by lazy {
        context.getSharedPreferences(SHARED_CONFIG, Context.MODE_PRIVATE)
    }

    override fun getString(key: SharedPreferencesItem): String? {
        return sharedPreferences.getString(key.name.lowercase(), null)
    }

    override fun setString(key: SharedPreferencesItem, value: String) {
        sharedPreferences.edit().putString(key.name.lowercase(), value).apply()
    }

    override fun <T : Any> getObject(key: SharedPreferencesItem): T? {
        val rawString = sharedPreferences.getString(key.name.lowercase(), null) ?: return null
        val type: Type = object : TypeToken<T>() {}.type
        return Gson().fromJson(rawString, type)
    }

    override fun setObject(key: SharedPreferencesItem, value: Any) {
        val rawString = Gson().toJson(value)
        sharedPreferences.edit().putString(key.name.lowercase(), rawString).apply()
    }

}