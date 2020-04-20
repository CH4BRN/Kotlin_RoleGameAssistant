// File IdealConverter.kt
// @Author pierre.antoine - 09/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.db_ideal

import androidx.room.TypeConverter
import com.google.gson.Gson

/**
 *   Class "IdealConverter" :
 *   TODO: Fill class use.
 **/
class DbIdealConverter {
    @TypeConverter
    fun convertIdealListToString(ideals: List<DbIdeal>): String {
        var idealsArray = arrayOfNulls<DbIdeal>(ideals.size)
        for (index in idealsArray.indices) {
            idealsArray[index] = ideals[index]
        }

        var str = ""

        var gson = Gson()

        for (index in idealsArray.indices) {
            var jsonString = gson.toJson(idealsArray[index])

            str += jsonString
            if (index < idealsArray.size - 1) {
                str += strSeparator
            }
        }
        return str
    }

    @TypeConverter
    fun convertStringToList(idealsString: String): List<DbIdeal> {
        var idealsArray = idealsString.split(strSeparator)
        var ideals = ArrayList<DbIdeal>()
        var gson = Gson()
        for (index in idealsArray.indices) {
            ideals.add(gson.fromJson(idealsArray[index], DbIdeal::class.java))
        }
        return ideals
    }

    companion object {
        const val strSeparator = "__,__"
    }
}