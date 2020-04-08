package com.imgur.android.ablum

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.imgur.android.R
import com.imgur.android.album.AlbumAdapter
import com.imgur.android.album.AlbumClickListener
import com.imgur.android.album.AlbumViewModel
import com.imgur.android.data.Album
import com.imgur.android.gallery.GalleryActivity
import kotlinx.android.synthetic.main.activity_album.*
import org.koin.android.ext.android.inject

/**
 * Activity used to display a list of albums
 */
class AlbumActivity : AppCompatActivity() {
    private val TAG = "AlbumActivity"

    private val viewModel: AlbumViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        attachObservers()
        setupRecyclerAdapter()

        viewModel.start("cats")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val search = menu.findItem(R.id.action_search)
        val searchView = search.actionView as SearchView
        searchView.setQueryHint(getString(R.string.search_albums))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.search(newText)
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    /**
     * Stats listing viewmodel's events so that it can make changes
     */
    private fun attachObservers() {
        viewModel.dataLoading.observe(
            this,
            Observer { loadingIndicatorPb.visibility = if (it) View.VISIBLE else View.GONE })

        viewModel.error.observe(
            this,
            Observer { noDataTextTv.text = it })

        viewModel.albums.observe(this, Observer {
            val adapter = albumRv.adapter as AlbumAdapter
            adapter.setList(it)
            noDataTextTv.visibility = if (it.isNotEmpty()) View.GONE else View.VISIBLE
        })

        viewModel.albumClickListener = object : AlbumClickListener {
            override fun onAlbumClick(album: Album) {
                Log.d(TAG, "onAlbumClick album=" + album)
                startActivity(GalleryActivity.getIntent(this@AlbumActivity, album.title!!, album.extractImages()))
            }
        }
    }

    /**
     * Setup adapter and spacing between items in recyclerview
     */
    private fun setupRecyclerAdapter() {
        albumRv.layoutManager = LinearLayoutManager(this)
        albumRv.adapter = AlbumAdapter(viewModel)
        albumRv.addItemDecoration(getItemDivider())
    }

    /**
     * Create basic item decorator which allows image drawable between items.
     */
    private fun getItemDivider(): DividerItemDecoration {
        val divider = ContextCompat.getDrawable(this, R.drawable.album_divider)
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        decoration.setDrawable(divider!!)
        return decoration
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, AlbumActivity::class.java)
        }
    }
}