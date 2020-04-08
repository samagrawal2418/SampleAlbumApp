package com.imgur.android.album

import android.os.Handler
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.imgur.android.Constant
import com.imgur.android.data.Album
import com.imgur.android.data.error.APIError
import com.imgur.android.data.source.RemoteDataSource
import com.imgur.android.data.source.Repository


class AlbumViewModel(private val repository: Repository) : ViewModel() {
    private val TAG = "AlbumViewModel"

    private val handler = Handler()

    //Live data for showing progress indicator
    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean>
        get() = _dataLoading

    //Live data for showing error message
    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    //Live data for showing album images
    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>>
        get() = _albums

    //listener's object for handling album item click
    var albumClickListener: AlbumClickListener? = null

    /**
     * First function to start list loading
     */
    fun start(query: String) {
        if(query.isEmpty()) {
            setList(ArrayList())
        } else {
            _dataLoading.value = true
            repository.getAlbums(query, object : RemoteDataSource.GetAlbumCallback {
                override fun onAlbumsLoaded(albums: List<Album>?, error: APIError?) {
                    _dataLoading.postValue(false)
                    if (error != null) {
                        _error.postValue(error.getMessage())
                    }
                    setList(albums)
                }
            })
        }
    }

    /**
     * Search album with given string.
     */
    fun search(query: String) {
        handler.removeCallbacksAndMessages(null)
        handler.postDelayed({
            start(query)
            Log.d(TAG, "search query=$query")
        }, Constant.QUERY_DEBOUNCE_DELAY)
    }

    private fun setList(albums: List<Album>?) {
        if(albums != null) {
            _albums.postValue(albums)
            Log.d(TAG, "setList size=" + albums.size)
        } else {
            _albums.postValue(ArrayList())
            Log.d(TAG, "setList size=0")
        }
    }
}
