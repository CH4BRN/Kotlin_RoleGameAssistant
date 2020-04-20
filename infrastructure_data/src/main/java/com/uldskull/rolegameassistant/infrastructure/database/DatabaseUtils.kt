// File DatabaseUtils.kt
// @Author pierre.antoine - 06/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database

import android.util.Log
import com.uldskull.rolegameassistant.infrastructure.dao.breed.DbBreedDao
import com.uldskull.rolegameassistant.infrastructure.dao.characteristic.DbBreedCharacteristicDao
import com.uldskull.rolegameassistant.infrastructure.dao.characteristic.DbRollCharacteristicsDao
import com.uldskull.rolegameassistant.infrastructure.dao.ideal.DbIdealsDao
import com.uldskull.rolegameassistant.infrastructure.database_model.db_breed.DbBreed
import com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic.DbBreedCharacteristic
import com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic.DbRollCharacteristic
import com.uldskull.rolegameassistant.infrastructure.database_model.db_ideal.DbIdeal
import com.uldskull.rolegameassistant.models.character.characteristic.CharacteristicsName

/**
 *   Class "DatabaseUtils" :
 *   TODO: Fill class use.
 **/
class DatabaseUtils {

    companion object {

        private const val TAG = "DatabaseUtils"

        fun populateRollCharacteristics(rollCharacteristicsDao: DbRollCharacteristicsDao) {
            Log.d(TAG, "populateRollCharacteristics")
            var dbRollCharacteristics = listOf(
                DbRollCharacteristic(
                    characteristicId = null,
                    characteristicMax = 24,
                    characteristicName = CharacteristicsName.APPEARANCE.toString(),
                    characteristicBonus = 0,
                    characteristicTotal = 0,
                    characteristicRoll = 0,
                    characteristicRollRule = "3D6"
                ),
                DbRollCharacteristic(
                    characteristicId = null,
                    characteristicMax = 24,
                    characteristicName = CharacteristicsName.CONSTITUTION.toString(),
                    characteristicBonus = 0,
                    characteristicTotal = 0,
                    characteristicRoll = 0,
                    characteristicRollRule = "3D6"
                ),
                DbRollCharacteristic(
                    characteristicId = null,
                    characteristicMax = 24,
                    characteristicName = CharacteristicsName.INTELLIGENCE.toString(),
                    characteristicBonus = 0,
                    characteristicTotal = 0,
                    characteristicRoll = 0,
                    characteristicRollRule = "2D6+6"
                ),
                DbRollCharacteristic(
                    characteristicId = null,
                    characteristicMax = 24,
                    characteristicName = CharacteristicsName.SIZE.toString(),
                    characteristicBonus = 0,
                    characteristicTotal = 0,
                    characteristicRoll = 0,
                    characteristicRollRule = "2D6+6"
                ),
                DbRollCharacteristic(
                    characteristicId = null,
                    characteristicMax = 24,
                    characteristicName = CharacteristicsName.DEXTERITY.toString(),
                    characteristicBonus = 0,
                    characteristicTotal = 0,
                    characteristicRoll = 0,
                    characteristicRollRule = "3D6"
                ),
                DbRollCharacteristic(
                    characteristicId = null,
                    characteristicMax = 24,
                    characteristicName = CharacteristicsName.STRENGTH.toString(),
                    characteristicBonus = 0,
                    characteristicTotal = 0,
                    characteristicRoll = 0,
                    characteristicRollRule = "3D6"
                ),
                DbRollCharacteristic(
                    characteristicId = null,
                    characteristicMax = 24,
                    characteristicName = CharacteristicsName.POWER.toString(),
                    characteristicBonus = 0,
                    characteristicTotal = 0,
                    characteristicRoll = 0,
                    characteristicRollRule = "3D6"
                )

            )
            var result = rollCharacteristicsDao.insertRollCharacteristics(dbRollCharacteristics)
            result.forEach {
                Log.d("Insert result", it.toString())
            }
        }

        /**
         * Populate the database with some characteristics.
         */
        fun populateBreedCharacteristics(breedCharacteristicDao: DbBreedCharacteristicDao) {
            Log.d(TAG, "populateBreedCharacteristics")
            var dbBreedCharacteristics = listOf(
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
            var result = breedCharacteristicDao.insertBreedCharacteristics(dbBreedCharacteristics)
            result.forEach {
                Log.d("Insert result", it.toString())
            }


        }

        /**
         * Populate database with some ideals.
         */
        fun populateIdeals(idealsDao: DbIdealsDao) {

            Log.d(TAG, "populateIdeals - Instantiates")
            var dbIdeals = listOf(
                DbIdeal(
                    idealId = null,
                    idealName = "Mean",
                    idealEvilPoints = 200,
                    idealGoodPoints = 0
                ),
                DbIdeal(
                    idealId = null,
                    idealName = "Kind",
                    idealEvilPoints = 0,
                    idealGoodPoints = 200
                )
            )

            var insertIdealsResults: MutableList<Long?> = mutableListOf()
            Log.d(TAG, "populateIdeals - Inserts")
            dbIdeals.forEach {
                var result: Long? = -1
                Log.d(TAG, "Ideal : $it")
                try {
                    result = idealsDao.insertIdeal(it)
                    Log.d(TAG, "Populate ideal insert result : $result")
                } catch (e: Exception) {
                    Log.e(TAG, "Inserting ideal failed.")
                    e.printStackTrace()
                    throw e
                }
                insertIdealsResults.add(result)
            }

            Log.d(TAG, "populateIdeals - Checks")
            insertIdealsResults.forEach {
                Log.d(TAG, "Ideal id : $it")
                try {
                    var ideal = idealsDao.getIdealById(it)
                    Log.d(TAG, "$ideal")
                } catch (e: Exception) {
                    Log.e(TAG, "Checking ideal failed.")
                    e.printStackTrace()
                    throw e
                }
            }
        }


        /**
         * Populate database with some breeds.
         */
        fun populateBreed(breedDao: DbBreedDao) {
            Log.d(TAG, "populateBreed")

            var dbBreeds = listOf(
                DbBreed(
                    breedName = "TestBreed 1",
                    breedId = null,
                    breedDescription = "Test breed number 1",
                    breedHealthBonus = 5
                )
                ,
                DbBreed(
                    breedName = "TestBreed 2",
                    breedId = null,
                    breedDescription = "Test  breed number 2",
                    breedHealthBonus = 4
                ),
                DbBreed(
                    breedName = "TestBreed 3",
                    breedId = null,
                    breedDescription = "Test  breed number 3",
                    breedHealthBonus = 3
                ),
                DbBreed(
                    breedName = "TestBreed 4",
                    breedId = null,
                    breedDescription = "Test  breed number 4",
                    breedHealthBonus = 2
                )

            )
            var result = breedDao.insertBreeds(dbBreeds)
            result.forEach {
                Log.d("Insert result", it.toString())
            }

        }
    }
}