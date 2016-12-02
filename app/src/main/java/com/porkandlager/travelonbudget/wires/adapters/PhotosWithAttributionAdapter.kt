package com.porkandlager.travelonbudget.wires.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.crashlytics.android.answers.Answers
import com.crashlytics.android.answers.CustomEvent
import com.porkandlager.travelonbudget.R
import com.porkandlager.travelonbudget.mvp.PhotoViewer.PhotoViewerActivity
import com.porkandlager.travelonbudget.wires.Constants
import com.porkandlager.travelonbudget.wires.models.beans.PhotoWithAttribution

import butterknife.BindView
import butterknife.ButterKnife

/**
 * Created by tista on 11/15/16.
 */

class PhotosWithAttributionAdapter(private val context: Context) : RecyclerView.Adapter<PhotosWithAttributionAdapter.ViewHolder>() {

    private var photos: List<PhotoWithAttribution>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val morePhotosView = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_photo_with_attribution, parent, false)

        return ViewHolder(morePhotosView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = photos!![position]

        Glide.with(context)
                .load(photo.url)
                .centerCrop()
                .into(holder.imageView!!)
    }

    override fun getItemCount(): Int {
        if (photos == null) {
            return 0
        }

        return photos!!.size
    }

    fun setPhotos(photos: List<PhotoWithAttribution>) {
        this.photos = photos
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.row_photos_image) var imageView: ImageView? = null

        init {
            ButterKnife.bind(this, itemView)

            itemView.setOnClickListener { view ->
                val photo = photos!![adapterPosition]

                // Log first
                Answers.getInstance().logCustom(CustomEvent("Photo Thumbnail Clicked")
                        .putCustomAttribute("Image Filename", photo.url))

                val intent = Intent(context, PhotoViewerActivity::class.java)
                intent.putExtra(Constants.PHOTO_VIEWER_PHOTO_URL,
                        photo.url)

                context.startActivity(intent)
            }
        }

    }

}
