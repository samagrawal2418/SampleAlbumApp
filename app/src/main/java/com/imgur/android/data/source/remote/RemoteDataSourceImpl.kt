package com.imgur.android.data.source.remote

import com.imgur.android.Constant
import com.imgur.android.data.Album
import com.imgur.android.data.error.APIError
import com.imgur.android.data.source.RemoteDataSource
import com.imgur.android.data.source.remote.retrofit.RetrofitFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException

/**
 * This class implements all APIs required and provided data.
 */
class RemoteDataSourceImpl : RemoteDataSource {

    override fun getAlbums(query: String, callback: RemoteDataSource.GetAlbumCallback) {
        val service = RetrofitFactory.createRetrofitService()
        GlobalScope.launch {
            val request = service.getAlbums(Constant.API_VERSION, query)
            try {
                val response = request.await()
                if (response.isSuccessful) {
                    val albums = response.body()?.data
                    val list = ArrayList<Album>()
                    if (albums != null) {
                        for (album in albums) {
                            if (album.isImageAlbum()) {
                                list.add(album)
                            }
                        }
                    }
                    callback.onAlbumsLoaded(list, null)
                } else {
                    callback.onAlbumsLoaded(null, parseError(response))
                }
            } catch (e: Throwable) {
                callback.onAlbumsLoaded(null, APIError.createError(e))
            }
        }
    }

    /**
     * Parse general purpose error response
     */
    fun parseError(response: Response<*>): APIError? {
        val converter: Converter<ResponseBody, APIError> = RetrofitFactory.createRetrofit()
            .responseBodyConverter(APIError::class.java, arrayOfNulls<Annotation>(0))

        val error: APIError?

        try {
            error = converter.convert(response.errorBody())
        } catch (e: IOException) {
            return APIError()
        }

        return error
    }
}