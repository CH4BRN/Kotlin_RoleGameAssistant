// File RollCharacteristicDatabaseUtil.kt
// @Author pierre.antoine - 27/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database.databaseUtils

import android.util.Log
import com.uldskull.rolegameassistant.infrastructure.dao.characteristic.DbRollCharacteristicsDao
import com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic.DbRollCharacteristic
import com.uldskull.rolegameassistant.models.character.characteristic.CharacteristicsName

/**
 *   Class "RollCharacteristicDatabaseUtil" :
 *   TODO: Fill class use.
 **/
class RollCharacteristicDatabaseUtil {
    companion object{
        private const val TAG = "RollCharacteristicDatabaseUtil"


        fun populateRollCharacteristics(rollCharacteristicsDao: DbRollCharacteristicsDao) {
            Log.d(TAG, "populateRollCharacteristics")
            var dbRollCharacteristics = listOf(
                DbRollCharacteristic(
                    characteristicName = CharacteristicsName.APPEARANCE.toString(),
                    characteristicRollRule = "3D6"
                ),
                DbRollCharacteristic(
                    characteristicName = CharacteristicsName.CONSTITUTION.toString(),
                    characteristicRollRule = "3D6"
                ),
                DbRollCharacteristic(
                    characteristicName = CharacteristicsName.INTELLIGENCE.toString(),
                    characteristicRollRule = "2D6+6"
                ),
                DbRollCharacteristic(
                    characteristicName = CharacteristicsName.SIZE.toString(),
                    characteristicRollRule = "2D6+6"
                ),
                DbRollCharacteristic(
                    characteristicName = CharacteristicsName.DEXTERITY.toString(),
                    characteristicRollRule = "3D6"
                ),
                DbRollCharacteristic(
                    characteristicName = CharacteristicsName.STRENGTH.toString(),
                    characteristicRollRule = "3D6"
                ),
                DbRollCharacteristic(
                    characteristicName = CharacteristicsName.POWER.toString(),
                    characteristicRollRule = "3D6"
                ),
                DbRollCharacteristic(
                    characteristicName = CharacteristicsName.EDUCATION.toString(),
                    characteristicRollRule = "3D6+6"
                )

            )
            var result = rollCharacteristicsDao.insertRollCharacteristics(dbRollCharacteristics)
            result.forEach {
                Log.d("Insert result", it.toString())
            }
        }
    }
// TODO : Fill class.
}