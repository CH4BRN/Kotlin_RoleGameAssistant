// GetBasicInfoById.kt created by UldSkull - 17/02/2020

package com.uldskull.rolegameassistant.domain_services

import com.uldskull.rolegameassistant.models.basic_info.DomainBasicInfo

/**
Class "GetBasicInfoById"

TODO: Describe class utility.
 */
class UseCaseGetBasicInfoById(



){



}

data class Request(
    val basicInfoId:Long?
)

sealed class Result<out T> {
    data class OnSuccess<out T>(val data: T) : Result<T>()
    data class OnError<out T>(val error: Error) : Result<T>()
}