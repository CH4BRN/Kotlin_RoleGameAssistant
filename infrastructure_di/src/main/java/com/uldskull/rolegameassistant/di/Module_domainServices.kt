// Module_domainServices.kt created by UldSkull - 16/02/2020

package com.uldskull.rolegameassistant.di

import com.uldskull.rolegameassistant.domain_services.BasicInfoDomainService
import com.uldskull.rolegameassistant.domain_services.BasicInfoDomainServiceImpl
import org.koin.dsl.module

val basicInfoDomainServicesModule = module {
    single<BasicInfoDomainService> { BasicInfoDomainServiceImpl(get()) }
}