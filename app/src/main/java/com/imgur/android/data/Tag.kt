package com.imgur.android.data

import com.squareup.moshi.Json

class Tag {
    @field:Json(name = "name")
    var name: String? = null
    @field:Json(name = "display_name")
    var displayName: String? = null
    @field:Json(name = "followers")
    var followers: Int? = 0
    @field:Json(name = "total_items")
    var totalItems: Int? = 0

    constructor(name: String?, displayName: String?, followers: Int?, totalItems: Int?) {
        this.name = name
        this.displayName = displayName
        this.followers = followers
        this.totalItems = totalItems
    }

    override fun toString(): String {
        return "Tag(name=$name, displayName=$displayName, followers=$followers, totalItems=$totalItems)"
    }

}