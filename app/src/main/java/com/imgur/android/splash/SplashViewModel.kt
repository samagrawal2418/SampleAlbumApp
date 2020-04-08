package com.imgur.android.splash

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.imgur.android.Constant

class SplashViewModel {
    private val _showing = MutableLiveData<Boolean>()
    val showing: LiveData<Boolean> = _showing

    init {
        _showing.value = true
        Handler().postDelayed({
            _showing.value = false
        }, Constant.SPLASH_DELAY.toLong())
    }
}
