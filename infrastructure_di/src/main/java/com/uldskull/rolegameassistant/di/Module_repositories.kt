// Module_repositories.kt created by UldSkull - 15/02/2020

package com.uldskull.rolegameassistant.di

import androidx.lifecycle.LiveData
<<<<<<< HEAD
import com.uldskull.rolegameassistant.models.basic_info.BasicInfoRepository
//import com.uldskull.rolegameassistant.infrastructure.repositories.basicinfo.BasicInfoRepositoryImpl
=======
import com.uldskull.rolegameassistant.infrastructure.repositories.basicinfo.BasicInfoRepositoryImpl
>>>>>>> e7ef618d5525040e5ea05d137966ac1ecc64df65
import com.uldskull.rolegameassistant.models.basic_info.DomainBasicInfo
import com.uldskull.rolegameassistant.repository.basic_info.BasicInfoRepository
import org.koin.dsl.module

val basicInfoRepositoriesModule = module {
   // single<BasicInfoRepository<LiveData<List<DomainBasicInfo>>>> {BasicInfoRepositoryImpl(get())  }
}