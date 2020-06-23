// File MainApplication.kt
// @Author pierre.antoine - 27/01/2020 - No copyright.

package com.uldskull.rolegameassistant.application

import android.app.Application
import android.util.Log
import com.uldskull.rolegameassistant.di.koin_modules.repositoriesModule
import com.uldskull.rolegameassistant.di.koin_modules.useCasesModule
import com.uldskull.rolegameassistant.di.koin_modules.useCasesServiceModule
import com.uldskull.rolegameassistant.di.koin_modules.viewModelModule
import com.uldskull.rolegameassistant.di.koin_modules.daoModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 *   Class "MainApplication" :
 *   App entry point.
 **/
class MainApplication : Application() {
    /** Application life cycle  **/
    override fun onCreate() {
        Log.d(TAG, "onCreate")
        super.onCreate()
        //  Start koin
        startKoin {
            androidContext(this@MainApplication)
            androidLogger()

            // Injects modules for DI
            //  Room
            modules(
                daoModule
            )
            //  Repositories
            modules(
                repositoriesModule
            )
            //  View models
            modules(
                viewModelModule
            )
            // UseCases
            modules(
                useCasesModule
            )
            //  UseCases service
            modules(
                useCasesServiceModule
            )
        }
    }

    companion object {
        const val TAG = "MainApplication"
    }
}