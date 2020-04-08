package com.imgur.android.data.source.remote.retrofit

import com.imgur.android.data.Album
import com.imgur.android.data.source.remote.response.OuterResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    @GET("/{api_version}/gallery/search")
    fun getAlbums(@Path("api_version") apiVersion: Int, @Query("q_all") query: String, @Query("q_type") type: String = "album"): Deferred<Response<OuterResponse<List<Album>>>>
}