package com.imgur.android.album

import com.imgur.android.data.Album

/**
 * Listener interface for album item click listening
 */
interface AlbumClickListener {
    fun onAlbumClick(album: Album)
}