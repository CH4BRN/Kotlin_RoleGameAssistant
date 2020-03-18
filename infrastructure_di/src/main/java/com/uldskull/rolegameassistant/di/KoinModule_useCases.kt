/** File KoinModule_useCases.kt
 *   @Author pierre.antoine - 18/03/2020 - No copyright.
 **/

package com.uldskull.rolegameassistant.di

import com.uldskull.rolegameassistant.useCases.diceRoll.GetOneDiceRollUseCase
import org.koin.dsl.module

val useCasesModule = module {
    factory { GetOneDiceRollUseCase() }
}