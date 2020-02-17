// File UseCaseGetAll.kt
// @Author pierre.antoine - 17/02/2020 - No copyright.

package com.uldskull.rolegameassistant.useCases

import androidx.lifecycle.LiveData
import com.uldskull.rolegameassistant.base.BaseUseCase
import com.uldskull.rolegameassistant.base.GetAllRequest
import com.uldskull.rolegameassistant.base.Response
import com.uldskull.rolegameassistant.models.basic_info.DomainBasicInfo
import com.uldskull.rolegameassistant.repository.basic_info.BasicInfoRepository

/**
 *   Class "UseCaseGetAll" :
 *   TODO: Fill class use.
 **/
class UseCaseGetAll(val repository: BasicInfoRepository<LiveData<List<DomainBasicInfo>>>) :
    BaseUseCase<GetAllRequest, LiveData<List<DomainBasicInfo>>>() {
    override suspend fun run(): Response<LiveData<List<DomainBasicInfo>>?> {
        return repository.getAll(request!!)
    }
// TODO : Fill class.
}