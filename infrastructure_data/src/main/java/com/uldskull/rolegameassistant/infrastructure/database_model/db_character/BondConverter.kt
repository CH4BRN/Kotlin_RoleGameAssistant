// File BondConverter.kt
// @Author pierre.antoine - 06/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.db_character

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.uldskull.rolegameassistant.infrastructure.database_model.db_bond.DbBond

/**
 *   Class "BondConverter" :
 *   TODO: Fill class use.
 **/
class BondConverter {
    @TypeConverter
    fun convertBondListToString(bonds: List<DbBond>): String {
        var bondsArray = arrayOfNulls<DbBond>(bonds.size)
        for (i in bondsArray.indices) {
            bondsArray[i] = bonds[i]
        }

        var str = ""

        var gson: Gson = Gson()
        for (i in bondsArray.indices) {
            var jsonString = gson.toJson(bondsArray[i])
            str += jsonString
            if (i < bondsArray.size - 1) {
                str += strSeparator
            }
        }
        return str
    }

    @TypeConverter
    fun convertStringToList(bondsString: String): List<DbBond> {
        var bondArray = bondsString.split(strSeparator)
        var bonds = ArrayList<DbBond>()
        var gson = Gson()
        for (i in bondArray.indices) {
            bonds.add(gson.fromJson(bondArray[i], DbBond::class.java))
        }
        return bonds
    }

    companion object {
        const val strSeparator = "__,__"
    }
}