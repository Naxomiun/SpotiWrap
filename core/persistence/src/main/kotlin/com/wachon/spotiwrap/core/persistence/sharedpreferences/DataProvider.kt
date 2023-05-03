package com.wachon.spotiwrap.core.persistence.sharedpreferences

interface DataProvider {
    fun getString(key: SharedPreferencesItem): String?
    fun setString(key: SharedPreferencesItem, value: String)
    fun <T: Any> getObject(key: SharedPreferencesItem): T?
    fun setObject(key: SharedPreferencesItem, value: Any)
}