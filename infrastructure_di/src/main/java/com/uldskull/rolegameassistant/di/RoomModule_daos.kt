// RoomModule_daos.kt created by UldSkull - 15/02/2020

package com.uldskull.rolegameassistant.di

import com.uldskull.rolegameassistant.infrastructure.database.AppDatabase
import org.koin.dsl.module

/** ViewModels injection    **/
val roomModule = module {

    //  BREED
    single { AppDatabase.getDatabase(get()).dbBreedDao() }
    //  CHARACTER
    single { AppDatabase.getDatabase(get()).dbCharacterDao() }
    //  CHARACTERISTICS
    single { AppDatabase.getDatabase(get()).dbCharacteristicDao() }
    single { AppDatabase.getDatabase(get()).dbBreedCharacteristicDao() }
    single { AppDatabase.getDatabase(get()).dbBreedWithDbBonusCharacteristicsDao() }
    single { AppDatabase.getDatabase(get()).dbRollCharacteristicsDao() }
    //  IDEALS
    single { AppDatabase.getDatabase(get()).dbIdealsDao() }
    //  JOBS
    single { AppDatabase.getDatabase(get()).dbOccupationsDao() }


}