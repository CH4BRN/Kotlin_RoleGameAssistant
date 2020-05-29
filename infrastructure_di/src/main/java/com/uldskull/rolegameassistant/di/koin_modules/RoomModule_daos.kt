// RoomModule_daos.kt created by UldSkull - 15/02/2020

package com.uldskull.rolegameassistant.di.koin_modules

import com.uldskull.rolegameassistant.infrastructure.database.AppDatabase
import org.koin.dsl.module

/** ViewModels injection    **/
val roomModule = module {

    //  BREED
    single { AppDatabase.getDatabase(get()).dbDisplayedBreedDao() }
    single { AppDatabase.getDatabase(get()).dbCharactersBreedDao() }
    //  CHARACTER
    single { AppDatabase.getDatabase(get()).dbCharacterDao() }
    single { AppDatabase.getDatabase(get()).dbCharacterWithBreedsDao() }
    //  CHARACTERISTICS
    single { AppDatabase.getDatabase(get()).dbCharacteristicDao() }
    single { AppDatabase.getDatabase(get()).dbBreedCharacteristicDao() }
    single { AppDatabase.getDatabase(get()).dbBreedWithDbBonusCharacteristicsDao() }
    single { AppDatabase.getDatabase(get()).dbRollCharacteristicsDao() }
    //  IDEALS
    single { AppDatabase.getDatabase(get()).dbIdealsDao() }
    //  OCCUPATIONS
    single { AppDatabase.getDatabase(get()).dbOccupationsDao() }
    single { AppDatabase.getDatabase(get ()).dbOccupationSkillDao() }
    single { AppDatabase.getDatabase(get()).dbOccupationsWithSkillsDao() }



}