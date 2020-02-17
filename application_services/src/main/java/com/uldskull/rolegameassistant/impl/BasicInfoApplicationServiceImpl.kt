// BasicInfoApplicationServiceImpl.kt created by UldSkull - 16/02/2020

package com.uldskull.rolegameassistant.impl

import androidx.lifecycle.LiveData
import com.uldskull.rolegameassistant.base.Response
import com.uldskull.rolegameassistant.contracts.BasicInfoApplicationService
import com.uldskull.rolegameassistant.models.basic_info.DomainBasicInfo
import com.uldskull.rolegameassistant.repository.basic_info.BasicInfoRepository
import com.uldskull.rolegameassistant.useCases.UseCaseGetCounter
import com.uldskull.rolegameassistant.usecases.counter.GetCounterRequest


/**
Class "BasicInfoApplicationServiceImpl"

TODO: Describe class utility.
 */
class BasicInfoApplicationServiceImpl(
    basicInfoRepository: BasicInfoRepository<LiveData<DomainBasicInfo>>,
    val useCaseGetCounter: UseCaseGetCounter
) : BasicInfoApplicationService {
    override suspend fun getCount(): Int? {
        val request = GetCounterRequest()
        val response = useCaseGetCounter.execute(request)
        if (response is Response.Success) {
            return response.data
        }
        return 0
    }


}