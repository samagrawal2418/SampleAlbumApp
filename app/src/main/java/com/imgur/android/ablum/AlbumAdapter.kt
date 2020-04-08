package com.imgur.android.album

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.imgur.android.R
import com.imgur.android.data.Album
import com.imgur.android.util.ImageHelper
import kotlinx.android.synthetic.main.album_item.view.*

/**
 * Album adapter to display album items
 */
class AlbumAdapter(private val viewModel: AlbumViewModel) :
    RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {
    private var albums: List<Album> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.album_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = albums.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(albums[position], viewModel.albumClickListener)

    /**
     * Update lists and notify recyclerview to update changes
     */
    fun setList(albums: List<Album>) {
        this.albums = albums
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val activity = itemView.context as Activity
        fun bind(album: Album, listener: AlbumClickListener?) {
            with(itemView) {
                albumTitleTv.text = album.title
                ImageHelper.loadCenterCropImage(albumThumbIv, album.getCoverImage()?.link)
                itemView.setOnClickListener {
                    listener?.onAlbumClick(album)
                }
            }
        }
    }
}