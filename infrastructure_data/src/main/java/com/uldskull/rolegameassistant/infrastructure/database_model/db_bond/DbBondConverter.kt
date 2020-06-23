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
            var bondsArray = arrayOfNulls<DbBond>(bonds.size)
            for (i in bondsArray.indices) {
                bondsArray[i] = bonds[i]
            }

            var gson: Gson = Gson()
            for (i in bondsArray.indices) {
                var jsonString = gson.toJson(bondsArray[i])
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
        var bondArray = bondsString.split(strSeparator)
        var bonds = ArrayList<DbBond>()
        var gson = Gson()
        for (i in bondArray.indices) {
            bonds.add(gson.fromJson(bondArray[i], DbBond::class.java))
        }
        return bonds
    }

}