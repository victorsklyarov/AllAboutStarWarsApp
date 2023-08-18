package com.zeroillusion.allaboutstarwarsapp.data.local

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromListStringToString(listString: List<String>): String = listString.toString()

    @TypeConverter
    fun fromStringToListString(stringList: String): List<String> {
        val result = ArrayList<String>()
        val split = stringList.replace("[", "").replace("]", "").replace(" ", "").split(",")
        for (string in split) {
            result.add(string)
        }
        return result
    }
}