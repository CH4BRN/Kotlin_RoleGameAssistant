// File BaseUseCase.kt
// @Author pierre.antoine - 17/02/2020 - No copyright.

package com.uldskull.rolegameassistant.base

/**
 *   Class "BAseUseCase" :
 *   TODO: Fill class use.
 **/
abstract class BaseUseCase<R : BaseRequest, T> {

    protected var request: R? = null

    suspend fun execute(request: R? = null): Response<T?> {
        this.request = request

        val validated = request?.validate() ?: true

        if (validated) return run()
        return Response.Error(
            IllegalArgumentException()
        )
    }

    abstract suspend fun run(): Response<T?>


// TODO : Fill class.
}