// File DbBreedConverter.kt
// @Author pierre.antoine - 15/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.db_breed

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.uldskull.rolegameassistant.infrastructure.database_model.db_ideal.DbIdealConverter.Companion.strSeparator

/**
 *   Class "DbBreedConverter" :
 *   TODO: Fill class use.
 **/
class DbBreedConverter {
    @TypeConverter
    fun convertBreedToString(breeds: List<DbBreed?>?): String {
        if (breeds != null) {
            val breedsArray = arrayOfNulls<DbBreed>(breeds.size)
            for (index in breedsArray.indices) {
                breedsArray[index] = breeds[index]
            }
            var str = ""
            var gson = Gson()
            for (index in breedsArray.indices) {
                var jsonString = gson.toJson(breedsArray[index])
                str += jsonString
                if (index < breedsArray.size - 1) {
                    str += strSeparator
                }
            }
            return str
        }
        return ""

    }

    @TypeConverter
    fun convertStringToList(breedsString: String): List<DbBreed> {
        var breedsArray = breedsString.split(strSeparator)
        var breeds = ArrayList<DbBreed>()
        var gson = Gson()
        for (index in breedsArray.indices) {
            breeds.add(gson.fromJson(breedsArray[index], DbBreed::class.java))
        }
        return breeds
    }
}