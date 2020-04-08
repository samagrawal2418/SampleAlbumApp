package com.imgur.android.gallery

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.imgur.android.data.source.Repository


class GalleryViewModel(private val repository: Repository) : ViewModel() {
    private val TAG = "GalleryViewModel"

    private val _images = MutableLiveData<List<String>>()
    val images: LiveData<List<String>>
        get() = _images

    var galleryClickListener: GalleryClickListener? = null

    fun setList(images: ArrayList<String>?) {
        if (images != null) {
            _images.postValue(images)
            Log.d(TAG, "setList size=" + images.size)
        } else {
            _images.postValue(ArrayList())
            Log.d(TAG, "setList size=0")
        }
    }
}
