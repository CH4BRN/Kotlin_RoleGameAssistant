// AppDatabase.kt created by UldSkull - 15/02/2020

package com.uldskull.rolegameassistant.infrastructure.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.uldskull.rolegameassistant.infrastructure.DatabaseName.DATABASE_NAME
import com.uldskull.rolegameassistant.infrastructure.dao.basic_info.BasicInfoDao
import com.uldskull.rolegameassistant.infrastructure.dao.basic_info.ClassDao
import com.uldskull.rolegameassistant.infrastructure.dao.basic_info.LevelDao
import com.uldskull.rolegameassistant.infrastructure.dao.basic_info.RaceDao
import com.uldskull.rolegameassistant.infrastructure.database_model.basic_info.DbBasicInfo
import com.uldskull.rolegameassistant.infrastructure.database_model.basic_info.DbClass
import com.uldskull.rolegameassistant.infrastructure.database_model.basic_info.DbLevel
import com.uldskull.rolegameassistant.infrastructure.database_model.basic_info.DbRace

/**
Class "AppDatabase"

Abstract class for romm database
 */
@Database(
    entities = [
        DbBasicInfo::class,
        DbClass::class,
        DbLevel::class,
        DbRace::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    /** DAO for basic info  */
    abstract fun basicInfoDao(): BasicInfoDao
    /** DAO for class   */
    abstract fun classDao():ClassDao
    /** DAO for level   */
    abstract fun levelDao():LevelDao
    /** DAO for race    */
    abstract fun raceDao():RaceDao

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