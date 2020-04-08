package com.imgur.android.data

import com.squareup.moshi.Json

class Image(
    @field:Json(name = "id") var id: String?, @field:Json(name = "title") var title: String?, @field:Json(
        name = "description"
    ) var description: String?, @field:Json(name = "link") var link: String?, @field:Json(name = "width") var width: Int?, @field:Json(
        name = "height"
    ) var height: Int?, @field:Json(name = "size") var size: Int?, @field:Json(name = "type") var type: String?, @field:Json(
        name = "datetime"
    ) var datetime: Int?
) {

    override fun toString(): String {
        return "Image(id=$id, title=$title, description=$description, link=$link, width=$width, height=$height, size=$size, type=$type, datetime=$datetime)"
    }
}