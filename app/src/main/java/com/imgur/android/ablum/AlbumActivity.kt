package com.imgur.android.ablum

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.imgur.android.R
import com.imgur.android.album.AlbumViewModel
import org.koin.android.ext.android.inject

class AlbumActivity : AppCompatActivity() {
    private val viewModel: AlbumViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        viewModel.start("cats")
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, AlbumActivity::class.java)
        }
    }
}
