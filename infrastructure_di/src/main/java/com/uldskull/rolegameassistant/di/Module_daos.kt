// Module_daos.kt created by UldSkull - 15/02/2020

package com.uldskull.rolegameassistant.di

import com.uldskull.rolegameassistant.infrastructure.database.AppDatabase
import org.koin.dsl.module

/** ViewModels injection    **/
val roomModule = module {

    single { AppDatabase.getDatabase(get()).basicInfoDao() }
    single { AppDatabase.getDatabase(get()).classDao() }
    single { AppDatabase.getDatabase(get()).levelDao()}
    single { AppDatabase.getDatabase(get()).raceDao()}
}