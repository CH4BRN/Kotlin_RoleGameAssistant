// KoinModules_domainServices.kt created by UldSkull - 23/06/2020

package com.uldskull.rolegameassistant.di.koin_modules

import com.uldskull.rolegameassistant.services.DiceService
import com.uldskull.rolegameassistant.services.diceRoll.DiceServiceImpl
import org.koin.dsl.module

/**
 * Use cases service module
 */
val useCasesServiceModule = module {
    /**
     * dice service di
     */
    /**
     * dice service di
     */
    single<DiceService> {
        DiceServiceImpl(
            get()
        )
    }
}