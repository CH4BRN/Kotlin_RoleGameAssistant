/** File KoinModule_useCases.kt
 *   @Author pierre.antoine - 18/03/2020 - No copyright.
 **/

package com.uldskull.rolegameassistant.di.koin_modules

import com.uldskull.rolegameassistant.useCases.diceRoll.DiceService
import com.uldskull.rolegameassistant.useCases.diceRoll.DiceServiceImpl
import com.uldskull.rolegameassistant.useCases.diceRoll.GetOneDiceRollUseCase
import org.koin.dsl.module

/**
 * Use cases module
 */
val useCasesModule = module {
    /**
     * Dice roll use case di
     */
    factory { GetOneDiceRollUseCase() }
}

/**
 * Use cases service module
 */
val useCasesServiceModule = module {
    /**
     * dice service di
     */
    single<DiceService> { DiceServiceImpl(get()) }
}