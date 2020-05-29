// File DbBreedConverter.kt
// @Author pierre.antoine - 15/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.db_breed.displayedBreeds

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.uldskull.rolegameassistant.infrastructure.database_model.db_ideal.DbIdealConverter.Companion.strSeparator

/**
 *   Class "DbBreedConverter" :
 *   TODO: Fill class use.
 **/
class DbDisplayedBreedConverter {
    @TypeConverter
    fun convertBreedToString(displayedBreeds: List<DbDisplayedBreed?>?): String {
        if (displayedBreeds != null) {
            val breedsArray = arrayOfNulls<DbDisplayedBreed>(displayedBreeds.size)
            for (index in breedsArray.indices) {
                breedsArray[index] = displayedBreeds[index]
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
    fun convertStringToList(breedsString: String): List<DbDisplayedBreed> {
        var breedsArray = breedsString.split(strSeparator)
        var breeds = ArrayList<DbDisplayedBreed>()
        var gson = Gson()
        for (index in breedsArray.indices) {
            breeds.add(gson.fromJson(breedsArray[index], DbDisplayedBreed::class.java))
        }
        return breeds
    }
}