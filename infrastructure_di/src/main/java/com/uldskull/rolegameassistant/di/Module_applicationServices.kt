/** File Module_applicationServices.kt
 *   @Author pierre.antoine - 17/02/2020 - No copyright.
 **/

package com.uldskull.rolegameassistant.di

import com.uldskull.rolegameassistant.contracts.BasicInfoApplicationService
import com.uldskull.rolegameassistant.impl.BasicInfoApplicationServiceImpl
import org.koin.dsl.module

val applicationServiceModule = module {
    single<BasicInfoApplicationService> { BasicInfoApplicationServiceImpl(get(), get()) }
}