package com.imgur.android.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.imgur.android.R
import com.imgur.android.ablum.AlbumActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        viewModel = SplashViewModel()
        viewModel.showing.observe(this, Observer { aBoolean ->
            if (!aBoolean!!) {
                startActivity(AlbumActivity.getIntent(this@SplashActivity))
            }
        })
    }
}
