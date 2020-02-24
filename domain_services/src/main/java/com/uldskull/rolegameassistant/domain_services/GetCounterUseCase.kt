// GetCounterUseCase.kt created by UldSkull - 17/02/2020

package com.uldskull.rolegameassistant.domain_services

import androidx.lifecycle.LiveData
import com.uldskull.rolegameassistant.models.basic_info.BasicInfoRepository

import com.uldskull.rolegameassistant.models.basic_info.DomainBasicInfo
import com.uldskull.rolegameassistant.models.basic_info.GetCounterRequest
import com.uldskull.rolegameassistant.models.basic_info.Response


/**
Class "GetCounterUseCase"

TODO: Describe class utility.
 */
class GetCounterUseCase(val repository: BasicInfoRepository<LiveData<List<DomainBasicInfo>>>) :
    BaseUseCase<GetCounterRequest, Int>() {
    override suspend fun run(): Response<Int> {
        return repository.getCounter(request!!)
    }

}