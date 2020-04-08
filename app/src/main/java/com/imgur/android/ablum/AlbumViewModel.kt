package com.imgur.android.album

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.imgur.android.album.AlbumClickListener
import com.imgur.android.data.Album
import com.imgur.android.data.error.APIError
import com.imgur.android.data.source.RemoteDataSource
import com.imgur.android.data.source.Repository


class AlbumViewModel(private val repository: Repository) : ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean>
        get() = _dataLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>>
        get() = _albums

    var albumClickListener: AlbumClickListener? = null

    fun start(query: String) {
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

    private fun setList(albums: List<Album>?) {
        if(albums != null) {
            _albums.postValue(albums)
        } else {
            _albums.postValue(ArrayList())
        }
    }
}
