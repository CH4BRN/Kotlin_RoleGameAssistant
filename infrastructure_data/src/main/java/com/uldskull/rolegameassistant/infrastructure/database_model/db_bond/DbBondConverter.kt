// File BondConverter.kt
// @Author pierre.antoine - 06/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database_model.db_bond

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.uldskull.rolegameassistant.infrastructure.database_model.DatabaseModelContracts.strSeparator

/**
 *   Class "BondConverter" :
 *   Converter bond list / json
 **/
class DbBondConverter {
    /**
     * Converts a bond list to a json string
     */
    @TypeConverter
    fun convertBondListToString(bonds: List<DbBond?>?): String {
        var str = ""
        if(bonds != null){
            val bondsArray = arrayOfNulls<DbBond>(bonds.size)
            for (i in bondsArray.indices) {
                bondsArray[i] = bonds[i]
            }

            val gson = Gson()
            for (i in bondsArray.indices) {
                val jsonString = gson.toJson(bondsArray[i])
                str += jsonString
                if (i < bondsArray.size - 1) {
                    str += strSeparator
                }
            }
        }

        return str
    }

    /**
     * converts a JSon string to a bond list
     */
    @TypeConverter
    fun convertStringToList(bondsString: String): List<DbBond> {
        val bondArray = bondsString.split(strSeparator)
        val bonds = ArrayList<DbBond>()
        val gson = Gson()
        for (i in bondArray.indices) {
            bonds.add(gson.fromJson(bondArray[i], DbBond::class.java))
        }
        return bonds
    }

}