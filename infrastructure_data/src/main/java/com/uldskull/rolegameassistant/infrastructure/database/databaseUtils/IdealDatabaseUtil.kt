// File IdealDatabaseUtil.kt
// @Author pierre.antoine - 27/04/2020 - No copyright.

package com.uldskull.rolegameassistant.infrastructure.database.databaseUtils

import android.util.Log
import com.uldskull.rolegameassistant.infrastructure.dao.ideal.DbIdealsDao
import com.uldskull.rolegameassistant.infrastructure.database_model.db_ideal.DbIdeal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 *   Class "IdealDatabaseUtil" :
 *   Database utils for ideals
 **/
class IdealDatabaseUtil {
    companion object {
        private const val TAG = "IdealDatabaseUtil"

        /**
         * Populate database with some ideals.
         */
        fun populateIdeals(idealsDao: DbIdealsDao) {

            Log.d(TAG, "populateIdeals - Instantiates")
            val dbIdeals = listOf(
                DbIdeal(
                    idealId = null,
                    idealName = "Mean",
                    idealEvilPoints = 200,
                    idealGoodPoints = 0,
                    isChecked = false
                ),
                DbIdeal(
                    idealId = null,
                    idealName = "Kind",
                    idealEvilPoints = 0,
                    idealGoodPoints = 200,
                    isChecked = false
                )
            )

            val insertIdealsResults: MutableList<Long?> = mutableListOf()
            Log.d(TAG, "populateIdeals - Inserts")
            val coroutineScope = CoroutineScope(Dispatchers.Main)
            coroutineScope.launch {
                dbIdeals.forEach {
                    var result: Long? = null
                    Log.d(TAG, "Ideal : $it")
                    try {
                        var oldIdeal = idealsDao.findIdealByName(it.idealName)
                        if (oldIdeal == null) {
                            result = idealsDao.insert(it)
                        } else {
                            //do nothing
                        }
                        Log.d(TAG, "Populate ideal insert result : $result")
                    } catch (e: Exception) {
                        Log.e(TAG, "Inserting ideal failed.")
                        e.printStackTrace()
                        throw e
                    }
                    insertIdealsResults.add(result)
                }
            }



            Log.d(TAG, "populateIdeals - Checks")
            insertIdealsResults.forEach {
                Log.d(TAG, "Ideal id : $it")
                try {
                    val coroutineScope = CoroutineScope(Dispatchers.Main)
                    coroutineScope.launch {
                        val ideal = idealsDao.getIdealById(it)
                        Log.d(TAG, "$ideal")
                    }

                } catch (e: Exception) {
                    Log.e(TAG, "Checking ideal failed.")
                    e.printStackTrace()
                    throw e
                }
            }
        }
    }
}