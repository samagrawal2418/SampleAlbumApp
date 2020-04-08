package com.imgur.android.ablum

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.imgur.android.R

class AlbumActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, AlbumActivity::class.java)
        }
    }
}
