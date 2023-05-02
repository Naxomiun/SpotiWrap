package com.wachon.spotiwrap.core.persistence.encrypted

interface EncryptedDataProvider {
    fun getEncryptedString(key: EncryptedItem): String?
    fun setEncryptedString(key: EncryptedItem, value: String)
    fun getEncryptedLong(key: EncryptedItem): Long?
    fun setEncryptedLong(key: EncryptedItem, value: Long)
    fun <T: Any> getCustomObject(key: EncryptedItem): T?
    fun <T: Any> setCustomObject(key: EncryptedItem, value: List<T>)
}