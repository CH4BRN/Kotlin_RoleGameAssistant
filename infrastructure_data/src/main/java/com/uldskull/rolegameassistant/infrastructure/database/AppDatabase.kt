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
import com.uldskull.rolegameassistant.infrastructure.dao.breed.DbBreedDao
import com.uldskull.rolegameassistant.infrastructure.dao.breed.DbBreedWithDbCharacteristicsDao
import com.uldskull.rolegameassistant.infrastructure.dao.characteristic.DbBreedCharacteristicDao
import com.uldskull.rolegameassistant.infrastructure.dao.characteristic.DbCharacteristicDao
import com.uldskull.rolegameassistant.infrastructure.database_model.DbCharacter
import com.uldskull.rolegameassistant.infrastructure.database_model.db_breed.DbBreed
import com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic.DbBreedCharacteristic
import com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic.DbCharacteristic
import com.uldskull.rolegameassistant.models.character.characteristic.CharacteristicsName
import kotlin.concurrent.thread

/**
Class "AppDatabase"

Abstract class for room database
 */
@Database(
    entities = [DbCharacter::class, DbBreed::class, DbCharacteristic::class, DbBreedCharacteristic::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    /**
     * Database characteristic DAO.
     */
    abstract fun dbCharacteristicDao(): DbCharacteristicDao

    /**
     * Database breed characteristic DAO
     */
    abstract fun dbBreedCharacteristicDao(): DbBreedCharacteristicDao

    /**
     * Database Breed DAO
     */
    abstract fun dbBreedDao(): DbBreedDao

    /**
     * Database Character DAO
     */
    abstract fun dbCharacterDao(): DbCharacterDao

    abstract fun dbBreedWithDbBonusCharacteristicsDao(): DbBreedWithDbCharacteristicsDao

    private class AppDatabaseCallback : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                thread(true) {
                    populateBreed(database.dbBreedDao())
                    populateBreedCharacteristics(database.dbBreedCharacteristicDao())
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

        fun populateBreedCharacteristics(breedCharacteristicDao: DbBreedCharacteristicDao) {
            var dbBreedCharacteristics = listOf(
                DbBreedCharacteristic(
                    characteristicId = null,
                    characteristicBonus = 1,
                    characteristicName = CharacteristicsName.STRENGTH.characteristicName,
                    characteristicBreedId = 1
                ),
                DbBreedCharacteristic(
                    characteristicId = null,
                    characteristicBonus = 1,
                    characteristicName = CharacteristicsName.POWER.characteristicName,
                    characteristicBreedId = 1
                ),
                DbBreedCharacteristic(
                    characteristicId = null,
                    characteristicBonus = 1,
                    characteristicName = CharacteristicsName.STRENGTH.characteristicName,
                    characteristicBreedId = 1
                ),
                DbBreedCharacteristic(
                    characteristicId = null,
                    characteristicBonus = 1,
                    characteristicName = CharacteristicsName.POWER.characteristicName,
                    characteristicBreedId = 1
                )
            )
            var result = breedCharacteristicDao.insertBreedCharacteristics(dbBreedCharacteristics)
            result.forEach {
                Log.d("Insert result", it.toString())
            }


        }


        fun populateBreed(breedDao: DbBreedDao) {

            var dbBreeds = listOf(
                DbBreed(
                    breedName = "TestBreed 1",
                    breedId = null,
                    breedDescription = "Test breed number 1"
                )/*
                ,
                DbBreed(
                    breedName = "TestBreed 2",
                    breedId = null,
                    breedDescription = "Test  breed number 2"
                ),
                DbBreed(
                    breedName = "TestBreed 3",
                    breedId = null,
                    breedDescription = "Test  breed number 3"
                ),
                DbBreed(
                    breedName = "TestBreed 4",
                    breedId = null,
                    breedDescription = "Test  breed number 4"
                )
                */
            )
            var result = breedDao.insertBreeds(dbBreeds)
            result.forEach {
                Log.d("Insert result", it.toString())
            }

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