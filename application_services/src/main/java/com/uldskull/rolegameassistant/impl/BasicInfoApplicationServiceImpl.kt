// BasicInfoApplicationServiceImpl.kt created by UldSkull - 16/02/2020

package com.uldskull.rolegameassistant.impl

import androidx.lifecycle.LiveData
import com.uldskull.rolegameassistant.contracts.BasicInfoApplicationService
import com.uldskull.rolegameassistant.contracts.repository.basic_info.BasicInfoRepository
import com.uldskull.rolegameassistant.models.basic_info.DomainBasicInfo

/**
Class "BasicInfoApplicationServiceImpl"

TODO: Describe class utility.
 */
class BasicInfoApplicationServiceImpl(
    basicInforRepository: BasicInfoRepository<LiveData<DomainBasicInfo>>
)
    :  BasicInfoApplicationService{

}