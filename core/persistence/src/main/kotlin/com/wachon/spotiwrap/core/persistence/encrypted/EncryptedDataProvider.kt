package com.wachon.spotiwrap.core.persistence.encrypted

interface EncryptedDataProvider {
    fun getEncryptedString(key: EncryptedItem): String?
    fun setEncryptedString(key: EncryptedItem, value: String)
    fun getAuthScopes(key: EncryptedItem): List<String>?
    fun setAuthScopes(key: EncryptedItem, value: List<String>)
}