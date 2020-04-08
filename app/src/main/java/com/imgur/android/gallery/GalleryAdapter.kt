package com.imgur.android.gallery

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.imgur.android.R
import com.imgur.android.data.Image
import com.imgur.android.util.ImageHelper
import kotlinx.android.synthetic.main.gallery_item.view.*

class GalleryAdapter(private val viewModel: GalleryViewModel) :
    RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {
    private var images: List<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.gallery_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(images[position], viewModel.galleryClickListener)

    fun setList(images: List<String>) {
        this.images = images
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val activity = itemView.context as Activity
        fun bind(image: String, listener: GalleryClickListener?) {
            with(itemView) {
                ImageHelper.loadCenterCropImage(galleryThumbIv, image)
                itemView.setOnClickListener {
                    listener?.onImageClick(image)
                }
            }
        }
    }
}