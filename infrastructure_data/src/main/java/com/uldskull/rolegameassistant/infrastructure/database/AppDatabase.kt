// AppDatabase.kt created by UldSkull - 15/02/2020

package com.uldskull.rolegameassistant.infrastructure.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.uldskull.rolegameassistant.infrastructure.DatabaseValues.DATABASE_NAME
import com.uldskull.rolegameassistant.infrastructure.dao.breed.DbDisplayedBreedDao
import com.uldskull.rolegameassistant.infrastructure.dao.breed.DbBreedWithDbCharacteristicsDao
import com.uldskull.rolegameassistant.infrastructure.dao.character.DbCharacterDao
import com.uldskull.rolegameassistant.infrastructure.dao.character.DbCharacterWithDbFilledSkillsDao
import com.uldskull.rolegameassistant.infrastructure.dao.characteristic.DbBreedCharacteristicDao
import com.uldskull.rolegameassistant.infrastructure.dao.characteristic.DbCharacteristicDao
import com.uldskull.rolegameassistant.infrastructure.dao.characteristic.DbRollCharacteristicsDao
import com.uldskull.rolegameassistant.infrastructure.dao.ideal.DbIdealsDao
import com.uldskull.rolegameassistant.infrastructure.dao.occupation.DbOccupationDbSkillDao
import com.uldskull.rolegameassistant.infrastructure.dao.occupation.DbOccupationsDao
import com.uldskull.rolegameassistant.infrastructure.dao.skill.DbFilledOccupationSkillDao
import com.uldskull.rolegameassistant.infrastructure.dao.skill.DbSkillToCheckDao
import com.uldskull.rolegameassistant.infrastructure.database.databaseUtils.BreedCharacteristicDatabaseUtil.Companion.populateBreedCharacteristics
import com.uldskull.rolegameassistant.infrastructure.database.databaseUtils.BreedDatabaseUtil.Companion.populateBreed
import com.uldskull.rolegameassistant.infrastructure.database.databaseUtils.IdealDatabaseUtil.Companion.populateIdeals
import com.uldskull.rolegameassistant.infrastructure.database.databaseUtils.OccupationDatabaseUtil.Companion.insertOccupations
import com.uldskull.rolegameassistant.infrastructure.database.databaseUtils.RollCharacteristicDatabaseUtil.Companion.populateRollCharacteristics
import com.uldskull.rolegameassistant.infrastructure.database.databaseUtils.SkillToCheckDatabaseUtil.Companion.populateSkillsToCheck
import com.uldskull.rolegameassistant.infrastructure.database_model.db_bond.DbBondConverter
import com.uldskull.rolegameassistant.infrastructure.database_model.db_breed.displayedBreeds.DbDisplayedBreed
import com.uldskull.rolegameassistant.infrastructure.database_model.db_breed.displayedBreeds.DbDisplayedBreedConverter
import com.uldskull.rolegameassistant.infrastructure.database_model.db_character.DbCharacter
import com.uldskull.rolegameassistant.infrastructure.database_model.db_character.DbCharacterConverter
import com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic.DbBreedCharacteristic
import com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic.DbCharacteristic
import com.uldskull.rolegameassistant.infrastructure.database_model.db_characteristic.DbRollCharacteristic
import com.uldskull.rolegameassistant.infrastructure.database_model.db_ideal.DbIdeal
import com.uldskull.rolegameassistant.infrastructure.database_model.db_ideal.DbIdealConverter
import com.uldskull.rolegameassistant.infrastructure.database_model.db_occupation.DbOccupation
import com.uldskull.rolegameassistant.infrastructure.database_model.db_occupation.DbOccupationAndDbSkillCrossRef
import com.uldskull.rolegameassistant.infrastructure.database_model.db_skill.DbFilledSkill
import com.uldskull.rolegameassistant.infrastructure.database_model.db_skill.DbSkillToCheck
import kotlin.concurrent.thread

/**
Class "AppDatabase"

Abstract class for room database
 */
@Database(
    entities = [
        DbCharacter::class,
        DbDisplayedBreed::class,
        DbCharacteristic::class,
        DbBreedCharacteristic::class,
        DbRollCharacteristic::class,
        DbIdeal::class,
        DbOccupation::class,
        DbSkillToCheck::class,
        DbFilledSkill::class,
        DbOccupationAndDbSkillCrossRef::class],
    version = 1
)
@TypeConverters(
    DbBondConverter::class,
    DbIdealConverter::class,
    DbDisplayedBreedConverter::class,
    DbCharacterConverter::class
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
     * Database roll characteristic DAO
     */
    abstract fun dbRollCharacteristicsDao(): DbRollCharacteristicsDao

    /**
     * Database displayed Breed DAO
     */
    abstract fun dbDisplayedBreedDao(): DbDisplayedBreedDao

    /**
     * Database Character DAO
     */
    abstract fun dbCharacterDao(): DbCharacterDao

    abstract fun dbCharacterWithDbFilledSkillsDao(): DbCharacterWithDbFilledSkillsDao

    abstract fun dbFilledSkillDao(): DbFilledOccupationSkillDao

    /**
     * Database Breed with characteristics DAO
     */
    abstract fun dbBreedWithDbBonusCharacteristicsDao(): DbBreedWithDbCharacteristicsDao

    /**
     * Database Ideals DAO
     */
    abstract fun dbIdealsDao(): DbIdealsDao

    /**
     * Database Occupations DAO
     */
    abstract fun dbOccupationsDao(): DbOccupationsDao

    /**
     * Database Occupation skilss DAO
     */
    abstract fun dbSkillToCheckDao(): DbSkillToCheckDao

    /**
     * Database Occupations with skills DAO
     */
    abstract fun dbOccupationsWithSkillsDao(): DbOccupationDbSkillDao


    private class AppDatabaseCallback : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                thread(true) {
                    populateBreed(database.dbDisplayedBreedDao())
                    populateBreedCharacteristics(database.dbBreedCharacteristicDao())
                    populateIdeals(database.dbIdealsDao())
                    populateRollCharacteristics(database.dbRollCharacteristicsDao())
                    populateSkillsToCheck(database?.dbSkillToCheckDao())
                    //populateOccupations(database.dbOccupationsDao())
                    //populateSkills(database.dbOccupationSkillDao())
                    insertOccupations(
                        occupationsDao = database.dbOccupationsDao(),
                        occupationSkillDao = database.dbSkillToCheckDao(),
                        occupationWithSkillDao = database.dbOccupationsWithSkillsDao()
                    )

                }
            }
        }
    }

    companion object {
        /**
         * Class tag
         */
        private const val TAG = "AppDatabase"

        /** Database Instance   */
        private var INSTANCE: AppDatabase? = null

        /** Get the instance    */
        fun getDatabase(context: Context): AppDatabase {
            Log.d(TAG, "getDatabase")

            if (INSTANCE == null) {
                INSTANCE = buildAppDatabase(context)
            }
            return INSTANCE!!
        }


        /** Build the database  */
        private fun buildAppDatabase(context: Context): AppDatabase {
            Log.d(TAG, "buildAppDatabase")
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