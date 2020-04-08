package com.imgur.android.data.source.remote.response

import com.squareup.moshi.Json

class OuterResponse<DATA>(
    @field:Json(name = "status") private val status: Int?, @field:Json(name = "success") val success: Boolean?, @field:Json(
        name = "data"
    ) val data: DATA
) {
    fun isSuccessful(): Boolean {
        return status == 200
    }
}