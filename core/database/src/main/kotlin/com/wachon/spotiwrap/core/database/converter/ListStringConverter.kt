package com.wachon.spotiwrap.core.database.converter

import androidx.room.TypeConverter

class ListStringConverter {

    @TypeConverter
    fun List<String>?.toStringData(): String? {
        if(this.isNullOrEmpty()) return null

        return this.joinToString(",")
    }

    @TypeConverter
    fun String?.toList(): List<String>? {
        if(this == null) return null

        return this.split(",")
    }
}