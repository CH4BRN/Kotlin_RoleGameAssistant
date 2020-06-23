// File DbBreedConverter.kt
// @Author pierre.antoine - 15/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.db_breed.displayedBreeds

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.uldskull.rolegameassistant.infrastructure.database_model.DatabaseModelContracts.strSeparator

/**
 *   Class "DbBreedConverter" :
 *   Converter DbBreed list / json string
 **/
class DbDisplayedBreedConverter {
    /**
     * Converts a breed list into a json string
     */
    @TypeConverter
    fun convertBreedToString(displayedBreeds: List<DbDisplayedBreed?>?): String {
        if (displayedBreeds != null) {
            val breedsArray = arrayOfNulls<DbDisplayedBreed>(displayedBreeds.size)
            for (index in breedsArray.indices) {
                breedsArray[index] = displayedBreeds[index]
            }
            var str = ""
            val gson = Gson()
            for (index in breedsArray.indices) {
                val jsonString = gson.toJson(breedsArray[index])
                str += jsonString
                if (index < breedsArray.size - 1) {
                    str += strSeparator
                }
            }
            return str
        }
        return ""

    }

    /**
     * Converts a json string into a breed list
     */
    @TypeConverter
    fun convertStringToList(breedsString: String): List<DbDisplayedBreed> {
        val breedsArray = breedsString.split(strSeparator)
        val breeds = ArrayList<DbDisplayedBreed>()
        val gson = Gson()
        for (index in breedsArray.indices) {
            breeds.add(gson.fromJson(breedsArray[index], DbDisplayedBreed::class.java))
        }
        return breeds
    }
}