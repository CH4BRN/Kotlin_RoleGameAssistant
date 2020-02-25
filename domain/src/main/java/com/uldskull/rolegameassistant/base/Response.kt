// File Response.kt
// @Author pierre.antoine - 17/02/2020 - No copyright.

package com.uldskull.rolegameassistant.base

/**
 *   Class "Response" :
 *   TODO: Fill class use.
 **/
sealed class Response<out T> {


    data class Error(
        val exception: Throwable,
        val code: Int? = null,
        val error: Boolean? = null,
        val errors: List<ErrorX>? = null,
        val message: String? = null,
        val method: String? = null,
        val path: String? = null
    ) : Response<Nothing>()
}

class Success<out T>(val data: T) : Response<T?>()

data class ErrorX(
    val message: String,
    val path: String
)