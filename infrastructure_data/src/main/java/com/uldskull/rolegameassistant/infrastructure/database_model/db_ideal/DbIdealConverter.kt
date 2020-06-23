// File IdealConverter.kt
// @Author pierre.antoine - 09/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.db_ideal

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.uldskull.rolegameassistant.infrastructure.database_model.DatabaseModelContracts.strSeparator

/**
 *   Class "IdealConverter" :
 *   Converter for ideal / gsonstring
 **/
class DbIdealConverter {
    /**
     * Converts an ideal list into a gson string
     */
    @TypeConverter
    fun convertIdealListToString(ideals: List<DbIdeal?>?): String {
        var str = ""
        if(ideals != null){
            val idealsArray = arrayOfNulls<DbIdeal>(ideals.size)
            for (index in idealsArray.indices) {
                idealsArray[index] = ideals[index]
            }
            val gson = Gson()
            for (index in idealsArray.indices) {
                val jsonString = gson.toJson(idealsArray[index])

                str += jsonString
                if (index < idealsArray.size - 1) {
                    str += strSeparator
                }
            }
        }
        return str
    }

    /**
     * Converts a string into a ideals list
     */
    @TypeConverter
    fun convertStringToList(idealsString: String): List<DbIdeal> {
        val idealsArray = idealsString.split(strSeparator)
        val ideals = ArrayList<DbIdeal>()
        val gson = Gson()
        for (index in idealsArray.indices) {
            ideals.add(gson.fromJson(idealsArray[index], DbIdeal::class.java))
        }
        return ideals
    }


}