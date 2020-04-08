package com.imgur.android.album

import com.imgur.android.data.Album

interface AlbumClickListener {
    fun onAlbumClick(album: Album)
}