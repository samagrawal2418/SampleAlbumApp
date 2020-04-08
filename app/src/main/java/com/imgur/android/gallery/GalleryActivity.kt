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
import kotlinx.android.synthetic.main.activity_gallery.*
import org.koin.android.ext.android.inject

/**
 * Activity class to display images inside album.
 */
class GalleryActivity : AppCompatActivity() {
    private val TAG = "GalleryActivity"

    private val viewModel: GalleryViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        supportActionBar?.title = intent.getStringExtra(Constant.GALLERY_TITLE);
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        attachObservers()
        setupRecyclerAdapter()

        viewModel.setList(intent.getStringArrayListExtra(Constant.GALLERY_IMAGES))
    }

    /**
     * Basic recyclerview setup like layout manager and adapter
     */
    private fun setupRecyclerAdapter() {
        galleryRv.layoutManager = GridLayoutManager(this, 2)
        galleryRv.adapter = GalleryAdapter(viewModel)
    }

    /**
     * Subscribe to the viewmodel events
     */
    private fun attachObservers() {
        viewModel.images.observe(this, Observer {
            val adapter = galleryRv.adapter as GalleryAdapter
            adapter.setList(it)
        })

        viewModel.galleryClickListener = object : GalleryClickListener {
            override fun onImageClick(image: String) {
                Log.d(TAG, "onImageClick image=" + image)
                startActivity(ImageViewerActivity.getIntent(this@GalleryActivity, image))
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        fun getIntent(context: Context, title: String, images: ArrayList<String>): Intent {
            val intent = Intent(context, GalleryActivity::class.java)
            intent.putExtra(Constant.GALLERY_TITLE, title)
            intent.putStringArrayListExtra(Constant.GALLERY_IMAGES, images)
            return intent
        }
    }
}