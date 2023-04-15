package com.wachon.spotiwrap.core.persistence.encrypted

interface EncryptedDataProvider {
    fun getEncryptedString(key: EncryptedItem): String?
    fun setEncryptedString(key: EncryptedItem, value: String)
}