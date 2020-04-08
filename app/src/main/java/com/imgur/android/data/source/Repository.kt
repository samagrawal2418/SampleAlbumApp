package com.imgur.android.data.source

/**
 * Centralized repository for all data
 */
class Repository(private val remoteDataSource: RemoteDataSource) : RemoteDataSource {
    override fun getAlbums(query: String, callback: RemoteDataSource.GetAlbumCallback) {
        remoteDataSource.getAlbums(query, callback)
    }
}