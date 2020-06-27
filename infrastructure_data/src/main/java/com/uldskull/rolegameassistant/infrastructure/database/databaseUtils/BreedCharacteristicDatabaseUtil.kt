// File BreedDatabaseUtil.kt
// @Author pierre.antoine - 27/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database.databaseUtils

import android.os.AsyncTask
import android.util.Log
import com.uldskull.rolegameassistant.infrastructure.dao.characteristic.DbBreedCharacteristicDao
import com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic.DbBreedCharacteristic
import com.uldskull.rolegameassistant.models.characteristic.CharacteristicsName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 *   Class "BreedDatabaseUtil" :
 *   Database utils for breed characteristics
 **/
class BreedCharacteristicDatabaseUtil {
    companion object {
        private const val TAG = "BreedDatabaseUtil"

        /**
         * Populate the database with some characteristics.
         */
        fun populateBreedCharacteristics(breedCharacteristicDao: DbBreedCharacteristicDao) {
            Log.d(TAG, "populateBreedCharacteristics")
            val dbBreedCharacteristics = listOf(
                DbBreedCharacteristic(
                    characteristicId = null,
                    characteristicBonus = 1,
                    characteristicName = CharacteristicsName.STRENGTH.toString(),
                    characteristicBreedId = 1
                ),
                DbBreedCharacteristic(
                    characteristicId = null,
                    characteristicBonus = 1,
                    characteristicName = CharacteristicsName.SIZE.toString(),
                    characteristicBreedId = 2
                )
            )
            var result: List<Long> = emptyList()
            val coroutineScope = CoroutineScope(Dispatchers.Main)
            coroutineScope.launch {
                result = breedCharacteristicDao.insert(dbBreedCharacteristics)
            }
            result.forEach {
                Log.d("Insert result", it.toString())
            }
        }
    }
}