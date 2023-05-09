package com.wachon.spotiwrap.core.persistence.sharedpreferences

interface DataProvider {
    fun getString(key: SharedPreferencesItem, decrypt: Boolean = false): String?
    fun setString(key: SharedPreferencesItem, value: String, encrypt: Boolean = false)
    fun getLong(key: SharedPreferencesItem): Long
    fun setLong(key: SharedPreferencesItem, value: Long)
    fun <T: Any> getObject(key: SharedPreferencesItem): T?
    fun setObject(key: SharedPreferencesItem, value: Any)
}