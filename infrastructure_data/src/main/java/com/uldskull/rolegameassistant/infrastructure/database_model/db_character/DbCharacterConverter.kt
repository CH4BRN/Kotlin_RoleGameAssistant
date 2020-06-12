// DbCharacterConverter.kt created by UldSkull - 10/06/2020

package com.uldskull.rolegameassistant.infrastructure.database_model.db_character

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.uldskull.rolegameassistant.infrastructure.database_model.databaseModelContracts.strSeparator

/**
Class "DbCharacterConverter"

TODO: Describe class utility.
 */
class DbCharacterConverter {
    @TypeConverter
    fun convertSkillsListToString(selectedSkills: List<Long?>?): String {
        var str = ""
        if (selectedSkills != null) {

            val skillsArray = arrayOfNulls<Long>(selectedSkills.size)
            for (index in skillsArray.indices) {
                skillsArray[index] = selectedSkills[index]
            }

            var gson = Gson()

            for (index in skillsArray.indices) {
                var jsonString = gson.toJson(skillsArray[index])
                str += jsonString
                if (index < skillsArray.size - 1) {
                    str += strSeparator
                }
            }

        }
        return str
    }

    @TypeConverter
    fun convertStringToList(skillsIdString: String):List<Long>{
        var skillsIdArray = skillsIdString.split(strSeparator)
        var skillsId = ArrayList<Long>()
        var gson = Gson()
        for(index in skillsIdArray.indices){
            skillsId.add(gson.fromJson(skillsIdArray[index], Long::class.java))
        }
        return skillsId
    }
}