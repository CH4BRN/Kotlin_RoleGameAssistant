// BaseUseCase.kt created by UldSkull - 17/02/2020

package com.uldskull.rolegameassistant.domain_services

import com.uldskull.rolegameassistant.models.basic_info.Response
import com.uldskull.rolegameassistant.useCases.basic_info.BaseRequest
import java.lang.IllegalArgumentException

/**
Class "BaseUseCase"

TODO: Describe class utility.
 */
abstract class BaseUseCase<R : BaseRequest, T> {
    protected var request: R? = null


    suspend fun execute(request: R? = null): Response<T> {
        this.request = request

        val validated = request?.validate() ?: true
        if (validated) return run()
        return Response.Error(IllegalArgumentException())

    }

    abstract suspend fun run(): Response<T>

}