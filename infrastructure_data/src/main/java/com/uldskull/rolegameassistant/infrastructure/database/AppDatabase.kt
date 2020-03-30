// AppDatabase.kt created by UldSkull - 15/02/2020

package com.uldskull.rolegameassistant.infrastructure.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues.DATABASE_NAME
import com.uldskull.rolegameassistant.infrastructure.dao.DbCharacterDao
import com.uldskull.rolegameassistant.infrastructure.dao.DbRaceDao
import com.uldskull.rolegameassistant.infrastructure.dao.DbRaceWithDbBonusCharacteristicsDao
import com.uldskull.rolegameassistant.infrastructure.dao.characteristic.DbCharacteristicDao
import com.uldskull.rolegameassistant.infrastructure.dao.characteristic.DbRaceCharacteristicDao
import com.uldskull.rolegameassistant.infrastructure.database_model.DbCharacter
import com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic.DbCharacteristic
import com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic.DbRaceCharacteristic
import com.uldskull.rolegameassistant.infrastructure.database_model.db_race.DbRace
import kotlin.concurrent.thread

/**
Class "AppDatabase"

Abstract class for room database
 */
@Database(
    entities = [DbCharacter::class, DbRace::class, DbCharacteristic::class, DbRaceCharacteristic::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    /**
     * Database characteristic DAO.
     */
    abstract fun dbCharacteristicDao(): DbCharacteristicDao

    /**
     * Database race characteristic DAO
     */
    abstract fun dbRaceCharacteristicDao(): DbRaceCharacteristicDao

    /**
     * Database Race DAO
     */
    abstract fun dbRaceDao(): DbRaceDao

    /**
     * Database Character DAO
     */
    abstract fun dbCharacterDao(): DbCharacterDao

    abstract fun dbRaceWithDbBonusCharacteristicsDao(): DbRaceWithDbBonusCharacteristicsDao

    private class AppDatabaseCallback : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                thread(true) {
                    populateRaces(database.dbRaceDao())
                }
            }
        }
    }

    companion object {
        /** Database Instance   */
        private var INSTANCE: AppDatabase? = null

        /** Get the instance    */
        fun getDatabase(context: Context): AppDatabase {
            Log.d("DATABASE", "getDatabase")

            if (INSTANCE == null) {
                INSTANCE = buildAppDatabase(context)
            }

            return INSTANCE!!
        }


        fun populateRaces(raceDao: DbRaceDao) {

            var races = listOf(
                DbRace(
                    raceName = "TestRace 1",
                    raceId = null,
                    raceDescription = "Test race number 1"
                ),
                DbRace(
                    raceName = "TestRace 2",
                    raceId = null,
                    raceDescription = "Test race number 2"
                ),
                DbRace(
                    raceName = "TestRace 3",
                    raceId = null,
                    raceDescription = "Test race number 3"
                ),
                DbRace(
                    raceName = "TestRace 4",
                    raceId = null,
                    raceDescription = "Test race number 4"
                )
            )

            raceDao.insertRaces(races)
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
                .addCallback(AppDatabaseCallback())
                .allowMainThreadQueries()
                .build()
        }
    }
}