package com.imgur.android.data.source

class Repository(val remoteDataSource: RemoteDataSource) : RemoteDataSource {
    override fun getAlbums(query: String, callback: RemoteDataSource.GetAlbumCallback) {
        remoteDataSource.getAlbums(query, callback)
    }
}