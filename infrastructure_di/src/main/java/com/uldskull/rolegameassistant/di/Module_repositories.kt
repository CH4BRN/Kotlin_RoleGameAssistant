// Module_repositories.kt created by UldSkull - 15/02/2020

package com.uldskull.rolegameassistant.di

import androidx.lifecycle.LiveData
import com.uldskull.rolegameassistant.models.basic_info.BasicInfoRepository
//import com.uldskull.rolegameassistant.infrastructure.repositories.basicinfo.BasicInfoRepositoryImpl
import com.uldskull.rolegameassistant.models.basic_info.DomainBasicInfo
import org.koin.dsl.module

val basicInfoRepositoriesModule = module {
   // single<BasicInfoRepository<LiveData<List<DomainBasicInfo>>>> {BasicInfoRepositoryImpl(get())  }
}