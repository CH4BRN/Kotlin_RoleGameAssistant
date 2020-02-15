// File MainApplication.kt
// @Author pierre.antoine - 27/01/2020 - No copyright.

package com.uldskull.rolegameassistant.application

import android.app.Application
import com.uldskull.rolegameassistant.di.roomModule

import com.uldskull.rolegameassistant.di.viewModelModule
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
        super.onCreate()
        //  Start koin
        startKoin {
            androidContext(this@MainApplication)
            androidLogger()

            // TODO("Inject modules here")
            modules(
                viewModelModule
            )
            modules(
                roomModule
            )

        }
    }
}