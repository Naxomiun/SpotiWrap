package com.wachon.spotiwrap.core.database.converter

import androidx.room.TypeConverter

class ListStringConverter {

    @TypeConverter
    fun List<String>.toStringData(): String {
        return this.joinToString(",")
    }

    @TypeConverter
    fun String.toList(): List<String> {
        return this.split(",")
    }
}