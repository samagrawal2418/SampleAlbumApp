package com.imgur.android.util

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.imgur.android.R
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import com.nostra13.universalimageloader.core.assist.QueueProcessingType
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener

object ImageHelper {
    fun loadCenterCropImage(imageView: ImageView, imagePath: String?) {
        ImageLoader.getInstance().displayImage(imagePath, imageView, getImageDio())
    }

    fun loadImage(imageView: ImageView, imagePath: String?, imageLoadingListener: SimpleImageLoadingListener? = null) {
        ImageLoader.getInstance().displayImage(imagePath, imageView, getImageDio(), imageLoadingListener)
    }

    fun initImageLoader(context: Context) {
        val config = ImageLoaderConfiguration.Builder(context)
        config.threadPriority(Thread.NORM_PRIORITY - 2)
        config.denyCacheImageMultipleSizesInMemory()
        config.diskCacheFileNameGenerator(Md5FileNameGenerator())
        config.diskCacheSize(50 * 1024 * 1024)
        config.tasksProcessingOrder(QueueProcessingType.LIFO)
        config.writeDebugLogs()
        ImageLoader.getInstance().init(config.build())
    }

    fun getImageDio(): DisplayImageOptions {
        return DisplayImageOptions.Builder()
            .showImageOnLoading(R.drawable.image_icon)
            .showImageForEmptyUri(R.drawable.image_icon)
            .showImageOnFail(R.drawable.image_icon)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .considerExifParams(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .build()
    }
}