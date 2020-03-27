// AppDatabase.kt created by UldSkull - 15/02/2020

package com.uldskull.rolegameassistant.infrastructure.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues.DATABASE_NAME
import com.uldskull.rolegameassistant.infrastructure.dao.DbCharacteristicDao
import com.uldskull.rolegameassistant.infrastructure.dao.DbRaceDao
import com.uldskull.rolegameassistant.infrastructure.database_model.DbRace
import com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic.DbBonusCharacteristic
import com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic.DbCharacteristic

/**
Class "AppDatabase"

Abstract class for room database
 */
@Database(
    entities = [DbCharacteristic::class, DbRace::class, DbBonusCharacteristic::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    /**
     * Database characteristic DAO
     */
    abstract fun dbCharacteristicDao(): DbCharacteristicDao

    /**
     * Database Race DAO
     */
    abstract fun dbRaceDao(): DbRaceDao

    companion object {
        /** Database Instance   */
        private var INSTANCE: AppDatabase? = null

        /** Get the instance    */
        fun getDatabase(context: Context): AppDatabase {
            Log.d("DATABASE", "getDatabase")
            synchronized(AppDatabase::class.java) {
                if (INSTANCE == null) {
                    INSTANCE = buildAppDatabase(context)
                }
            }
            return INSTANCE!!
        }

        /** Build the database  */
        private fun buildAppDatabase(context: Context): AppDatabase {
            Log.d("DATABASE", "buildAppDatabase")
            return Room
                .databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                )
                .allowMainThreadQueries()
                .build()
        }
    }
}