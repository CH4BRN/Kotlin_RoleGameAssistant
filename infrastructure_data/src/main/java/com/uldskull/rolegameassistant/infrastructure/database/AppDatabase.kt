// AppDatabase.kt created by UldSkull - 15/02/2020

package com.uldskull.rolegameassistant.infrastructure.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues.DATABASE_NAME
import com.uldskull.rolegameassistant.infrastructure.dao.BasicInfoDao
import com.uldskull.rolegameassistant.infrastructure.database_model.basic_info.DbBasicInformation

/**
Class "AppDatabase"

Abstract class for room database
 */
@Database(
    entities = [DbBasicInformation::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    /** DAO for basic info  */
    abstract fun basicInfoDao(): BasicInfoDao

    companion object {
        /** Database Instance   */
        private var INSTANCE: AppDatabase? = null

        /** Get the instance    */
        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = buildAppDatabase(context)
            }
            return INSTANCE!!
        }

        /** Build the database  */
        private fun buildAppDatabase(context: Context): AppDatabase {
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