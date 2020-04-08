package com.imgur.android

import android.app.Application
import android.content.Context
import com.imgur.android.album.AlbumViewModel
import com.imgur.android.splash.SplashViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import com.imgur.android.data.source.RemoteDataSource
import com.imgur.android.data.source.Repository
import com.imgur.android.data.source.remote.FakeRemoteDataSourceImpl
import com.imgur.android.data.source.remote.RemoteDataSourceImpl
import com.imgur.android.util.ImageHelper

class ImgurApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        context = this

        startKoin {
            androidContext(this@ImgurApplication)
            modules(appModule)
        }
        ImageHelper.initImageLoader(this)
    }

    val appModule = module {
        single<RemoteDataSource> { RemoteDataSourceImpl() }
        single { Repository(get()) }

        factory { SplashViewModel() }
        factory { AlbumViewModel(get()) }
    }

    companion object {
        public lateinit var context: Context
    }
}