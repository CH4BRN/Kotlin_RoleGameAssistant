// File BreedDatabaseUtil.kt
// @Author pierre.antoine - 27/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database.databaseUtils

import android.util.Log
import com.uldskull.rolegameassistant.infrastructure.dao.breed.DbDisplayedBreedDao
import com.uldskull.rolegameassistant.infrastructure.database_model.db_breed.displayedBreeds.DbDisplayedBreed

/**
 *   Class "BreedDatabaseUtil" :
 *   Database utils for breed
 **/
class BreedDatabaseUtil {
    companion object{
        private const val TAG= "BreedDatabaseUtil"

        /**
         * Populate database with some breeds.
         */
        fun populateBreed(displayedBreedDao: DbDisplayedBreedDao) {
            Log.d(TAG, "populateBreed")

            val dbBreeds = listOf(
                DbDisplayedBreed(
                    breedName = "TestBreed 1",
                    breedId = null,
                    breedDescription = "Test breed number 1",
                    breedHealthBonus = 5
                )
                ,
                DbDisplayedBreed(
                    breedName = "TestBreed 2",
                    breedId = null,
                    breedDescription = "Test  breed number 2",
                    breedHealthBonus = 4
                ),
                DbDisplayedBreed(
                    breedName = "TestBreed 3",
                    breedId = null,
                    breedDescription = "Test  breed number 3",
                    breedHealthBonus = 3
                ),
                DbDisplayedBreed(
                    breedName = "TestBreed 4",
                    breedId = null,
                    breedDescription = "Test  breed number 4",
                    breedHealthBonus = 2
                )

            )
            dbBreeds.forEach {
                if(displayedBreedDao.getBreedById(it.breedId) == null){
                    displayedBreedDao.insert(it)
                    Log.d("Insert result", it.toString())
                }

            }
        }
    }
}