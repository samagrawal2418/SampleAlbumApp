package com.imgur.android.data.source.remote.retrofit

import com.imgur.android.BuildConfig
import com.imgur.android.Constant
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.timeunit.TimeUnit
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitFactory {
    fun getOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(interceptor)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(Interceptor {
                val newRequest = it.request().newBuilder()
                    .addHeader("Authorization", "Client-ID " + BuildConfig.CLIENT_ID)
                    .build()
                it.proceed(newRequest)
            })

        return builder.build()
    }

    fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(getOkHttpClient())
            .build()
    }

    fun createRetrofitService(): RetrofitService {
        return createRetrofit().create(RetrofitService::class.java)
    }
}