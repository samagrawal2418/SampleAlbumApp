package com.imgur.android.data.source

import com.imgur.android.data.Album
import com.imgur.android.data.error.APIError

interface RemoteDataSource {
    interface GetAlbumCallback {
        fun onAlbumsLoaded(albums: List<Album>?, error: APIError?)
    }

    fun getAlbums(query: String, callback: GetAlbumCallback)
}