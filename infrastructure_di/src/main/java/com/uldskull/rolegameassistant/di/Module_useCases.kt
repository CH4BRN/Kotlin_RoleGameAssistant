/** File Module_useCases.kt
 *   @Author pierre.antoine - 17/02/2020 - No copyright.
 **/

package com.uldskull.rolegameassistant.di

import com.uldskull.rolegameassistant.useCases.UseCaseGetCounter
import org.koin.dsl.module

val useCaseModule = module {
    factory { UseCaseGetCounter(get()) }
}