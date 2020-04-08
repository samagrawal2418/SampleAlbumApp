package com.imgur.android.data.error

import com.imgur.android.Constant
import com.squareup.moshi.Json

/**
 * General purpose API error handler class
 */
open class APIError(
    @field:Json(name = "error")
    var error: String? = null,
    @field:Json(name = "request")
    var request: String? = null,
    @field:Json(name = "method")
    var method: String? = null
) {

    fun getMessage(): String {
        return (if (error == null) "" else error) as String
    }

    override fun toString(): String {
        return "APIError(error=$error, request=$request, method=$method)"
    }

    companion object {
        fun createError() : APIError {
            return APIError(Constant.UNKNOWN_API_ERROR)
        }
        fun createError(error: String) : APIError {
            return APIError(error)
        }
        fun createError(t: Throwable) : APIError {
            return APIError(if(t.localizedMessage != null) t.localizedMessage else Constant.UNKNOWN_API_ERROR)
        }
    }
}