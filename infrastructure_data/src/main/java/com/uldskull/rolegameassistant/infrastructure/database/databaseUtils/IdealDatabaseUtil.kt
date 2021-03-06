// File IdealDatabaseUtil.kt
// @Author pierre.antoine - 27/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database.databaseUtils

import android.util.Log
import com.uldskull.rolegameassistant.infrastructure.dao.ideal.DbIdealsDao
import com.uldskull.rolegameassistant.infrastructure.database_model.db_ideal.DbIdeal

/**
 *   Class "IdealDatabaseUtil" :
 *   TODO: Fill class use.
 **/
class IdealDatabaseUtil {
    companion object{
        private const val TAG = "IdealDatabaseUtil"
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
                var result: Long?
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
    }
// TODO : Fill class.
}