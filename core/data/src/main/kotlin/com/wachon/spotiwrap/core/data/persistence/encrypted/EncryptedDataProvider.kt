package com.wachon.spotiwrap.core.data.persistence.encrypted

interface EncryptedDataProvider {
    fun getEncryptedString(key: String): String?
    fun setEncryptedString(key: EncryptedItem, value: String)
}