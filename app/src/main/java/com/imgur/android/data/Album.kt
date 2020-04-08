package com.imgur.android.data

import com.squareup.moshi.Json

class Album(
    @field:Json(name = "id") var id: String?, @field:Json(name = "title") var title: String?, @field:Json(
        name = "description"
    ) var description: String?, @field:Json(name = "cover") var cover: String?, @field:Json(name = "link") var link: String?, @field:Json(
        name = "ups"
    ) var ups: Int?, @field:Json(name = "downs") var downs: Int?, @field:Json(name = "points") var points: Int?, @field:Json(
        name = "score"
    ) var score: Int?, @field:Json(name = "views") var views: Int?, @field:Json(name = "comment_count") var commentCount: Int?, @field:Json(
        name = "favorite_count"
    ) var favoriteCount: Int?, @field:Json(name = "images_count") var imagesCount: Int?, @field:Json(
        name = "is_album"
    ) var isAlbum: Boolean?, @field:Json(name = "datetime") var datetime: Int?, @field:Json(name = "tags") var tags: List<Tag>?, @field:Json(
        name = "images"
    ) var images: List<Image>?
) {

    fun getCoverImage(): Image? {
        if (images != null) {
            for (image in images!!) {
                if (image.type?.startsWith("image")!!) {
                    return image
                }
            }
        }
        return null
    }

    fun isImageAlbum(): Boolean {
        return isAlbum != null && isAlbum == true && getCoverImage() != null
    }

    fun extractImages(): ArrayList<String> {
        val list = ArrayList<String>()
        if (images != null) {
            for (image in images!!) {
                if (image.type?.startsWith("image")!!) {
                    list.add(image.link!!)
                }
            }
        }
        return list
    }

    override fun toString(): String {
        return "Album(id=$id, title=$title, description=$description, cover=$cover, link=$link, ups=$ups, downs=$downs, points=$points, score=$score, views=$views, commentCount=$commentCount, favoriteCount=$favoriteCount, imagesCount=$imagesCount, isAlbum=$isAlbum, datetime=$datetime, tags=$tags, images=$images)"
    }
}