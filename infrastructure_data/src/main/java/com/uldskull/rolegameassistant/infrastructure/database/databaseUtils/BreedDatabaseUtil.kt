// File BreedDatabaseUtil.kt
// @Author pierre.antoine - 27/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database.databaseUtils

import android.util.Log
import com.uldskull.rolegameassistant.infrastructure.dao.breed.DbBreedDao
import com.uldskull.rolegameassistant.infrastructure.database_model.db_breed.DbBreed

/**
 *   Class "BreedDatabaseUtil" :
 *   TODO: Fill class use.
 **/
class BreedDatabaseUtil {
    companion object{
        private const val TAG= "BreedDatabaseUtil"

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
// TODO : Fill class.
}