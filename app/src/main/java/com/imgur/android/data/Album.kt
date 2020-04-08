package com.imgur.android.data

import com.squareup.moshi.Json

class Album {
    @field:Json(name = "id")
    var id: String? = null
    @field:Json(name = "title")
    var title: String? = null
    @field:Json(name = "description")
    var description: String? = null
    @field:Json(name = "cover")
    var cover: String? = null
    @field:Json(name = "link")
    var link: String? = null
    @field:Json(name = "ups")
    var ups: Int? = 0
    @field:Json(name = "downs")
    var downs: Int? = 0
    @field:Json(name = "points")
    var points: Int? = -1
    @field:Json(name = "score")
    var score: Int? = 0
    @field:Json(name = "views")
    var views: Int? = 0
    @field:Json(name = "comment_count")
    var commentCount: Int? = 0
    @field:Json(name = "favorite_count")
    var favoriteCount: Int? = 0
    @field:Json(name = "images_count")
    var imagesCount: Int? = 0
    @field:Json(name = "is_album")
    var isAlbum: Boolean? = null
    @field:Json(name = "datetime")
    var datetime: Int? = 0
    @field:Json(name = "tags")
    var tags: List<Tag>? = null
    @field:Json(name = "images")
    var images: List<Image>? = null

    constructor(
        id: String?,
        title: String?,
        description: String?,
        cover: String?,
        link: String?,
        ups: Int?,
        downs: Int?,
        points: Int?,
        score: Int?,
        views: Int?,
        commentCount: Int?,
        favoriteCount: Int?,
        imagesCount: Int?,
        isAlbum: Boolean?,
        datetime: Int?,
        tags: List<Tag>?,
        images: List<Image>?
    ) {
        this.id = id
        this.title = title
        this.description = description
        this.cover = cover
        this.link = link
        this.ups = ups
        this.downs = downs
        this.points = points
        this.score = score
        this.views = views
        this.commentCount = commentCount
        this.favoriteCount = favoriteCount
        this.imagesCount = imagesCount
        this.isAlbum = isAlbum
        this.datetime = datetime
        this.tags = tags
        this.images = images
    }

    fun getCoverImage() : Image? {
        if(images != null) {
            for (image in images!!) {
                if(image.type?.startsWith("image")!!) {
                    return image
                }
            }
        }
        return null
    }

    fun isImageAlbum(): Boolean {
        return isAlbum != null && isAlbum == true && getCoverImage() != null
    }

    override fun toString(): String {
        return "Album(id=$id, title=$title, description=$description, cover=$cover, link=$link, ups=$ups, downs=$downs, points=$points, score=$score, views=$views, commentCount=$commentCount, favoriteCount=$favoriteCount, imagesCount=$imagesCount, isAlbum=$isAlbum, datetime=$datetime, tags=$tags, images=$images)"
    }
}