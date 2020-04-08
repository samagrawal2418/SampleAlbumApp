package com.imgur.android.gallery

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.imgur.android.Constant
import com.imgur.android.R
import com.imgur.android.util.ImageHelper
import kotlinx.android.synthetic.main.activity_gallery.*
import kotlinx.android.synthetic.main.activity_image_viewer.*
import kotlinx.android.synthetic.main.gallery_item.view.*
import org.koin.android.ext.android.inject


class ImageViewerActivity : AppCompatActivity() {
    private val TAG = "ImageViewerActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_viewer)

        supportActionBar?.title = getString(R.string.image_viewer)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val image = intent.getStringExtra(Constant.IMAGE_VIEWER_IMAGE);
        Log.d(TAG, "onCreate image=" + image)
        ImageHelper.loadCenterCropImage(fullImageTiv, image)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        fun getIntent(context: Context, image: String): Intent {
            val intent = Intent(context, ImageViewerActivity::class.java)
            intent.putExtra(Constant.IMAGE_VIEWER_IMAGE, image)
            return intent;
        }
    }
}