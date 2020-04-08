package com.imgur.android.data

import com.squareup.moshi.Json

class Tag(
    @field:Json(name = "name") var name: String?, @field:Json(name = "display_name") var displayName: String?, @field:Json(
        name = "followers"
    ) var followers: Int?, @field:Json(name = "total_items") var totalItems: Int?
) {

    override fun toString(): String {
        return "Tag(name=$name, displayName=$displayName, followers=$followers, totalItems=$totalItems)"
    }

}