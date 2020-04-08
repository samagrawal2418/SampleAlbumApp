package com.imgur.android.data.source.remote

import android.os.Handler
import com.imgur.android.data.Album
import com.imgur.android.data.Image
import com.imgur.android.data.Tag
import com.imgur.android.data.source.RemoteDataSource

/**
 * Fake data class for testing as well as local UI development
 */
class FakeRemoteDataSourceImpl : RemoteDataSource {
    private val WEB_SERVICE_DELAY: Long = 2000;
    private val HANDLER: Handler = Handler()

    override fun getAlbums(query: String, callback: RemoteDataSource.GetAlbumCallback) {
        HANDLER.postDelayed(Runnable {
            val tag = Tag("cats", "cats", 192290, 99442)
            val image1 = Image("u96M0yz", null, "Guess I've just been home wayyyy to much lately!", "https://i.imgur.com/u96M0yz.jpg", 1986, 2718, 2451800, "image/jpeg", 1586318089)
            val image2 = Image("BjIJDRT", null, "that's coffee in there.. lol", "https://i.imgur.com/BjIJDRT.jpg", 1986, 2718, 2451800, "image/jpeg", 1586318089)
            val album1 = Album("6o9vxia", "Starting to look like my cats...", null, "BjIJDRT", "https://imgur.com/a/6o9vxia", 1, 2, -1, 0, 19, 0, 0, 2, true, 1586318234, listOf(tag), listOf(image1, image2))
            val album2 = Album("fuk064s", "Some things that look like my cat, Mister Pants", null, "BjIJDRT", "https://imgur.com/a/6o9vxia", 1, 2, -1, 0, 19, 0, 0, 2, true, 1586318234, listOf(tag), listOf(image1))
            callback.onAlbumsLoaded(listOf(album1, album2), null);
        }, WEB_SERVICE_DELAY)
    }
}