package com.imgur.android.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.imgur.android.R
import com.imgur.android.ablum.AlbumActivity
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity() {
    private val viewModel: SplashViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        viewModel.showing.observe(this, Observer { aBoolean ->
            if (!aBoolean!!) {
                startActivity(AlbumActivity.getIntent(this@SplashActivity))
                finish()
            }
        })
    }
}
