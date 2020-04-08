package com.imgur.android.data

import com.squareup.moshi.Json

class Image {
    @field:Json(name = "id")
    var id: String? = null
    @field:Json(name = "title")
    var title: String? = null
    @field:Json(name = "description")
    var description: String? = null
    @field:Json(name = "link")
    var link: String? = null
    @field:Json(name = "width")
    var width: Int? = 0
    @field:Json(name = "height")
    var height: Int? = 0
    @field:Json(name = "size")
    var size: Int? = 0
    @field:Json(name = "type")
    var type: String? = null
    @field:Json(name = "datetime")
    var datetime: Int? = 0

    constructor(
        id: String?,
        title: String?,
        description: String?,
        link: String?,
        width: Int?,
        height: Int?,
        size: Int?,
        type: String?,
        datetime: Int?
    ) {
        this.id = id
        this.title = title
        this.description = description
        this.link = link
        this.width = width
        this.height = height
        this.size = size
        this.type = type
        this.datetime = datetime
    }

    override fun toString(): String {
        return "Image(id=$id, title=$title, description=$description, link=$link, width=$width, height=$height, size=$size, type=$type, datetime=$datetime)"
    }
}