// File UseCaseGetCounter.kt
// @Author pierre.antoine - 17/02/2020 - No copyright.

package com.uldskull.rolegameassistant.useCases

import androidx.lifecycle.LiveData
import com.uldskull.rolegameassistant.base.BaseUseCase
import com.uldskull.rolegameassistant.base.Response
import com.uldskull.rolegameassistant.models.basic_info.DomainBasicInfo
import com.uldskull.rolegameassistant.repository.basic_info.BasicInfoRepository
import com.uldskull.rolegameassistant.usecases.counter.GetCounterRequest

/**
 *   Class "UseCaseGetCounter" :
 *   TODO: Fill class use.
 **/
class UseCaseGetCounter(val repository: BasicInfoRepository<LiveData<List<DomainBasicInfo>>>) :
    BaseUseCase<GetCounterRequest, Int>() {
    override suspend fun run(): Response<Int?> {
        return repository.getCounter(request!!)
    }
// TODO : Fill class.
}