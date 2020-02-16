// Module_domainServices.kt created by UldSkull - 16/02/2020

package com.uldskull.rolegameassistant.di

import com.uldskull.rolegameassistant.use_cases.BasicInfoDomainService
import com.uldskull.rolegameassistant.use_cases.BasicInfoDomainServiceImpl
import org.koin.dsl.module

val basicInfoDomainServicesModule = module {
    single<BasicInfoDomainService> { BasicInfoDomainServiceImpl(get()) }
}