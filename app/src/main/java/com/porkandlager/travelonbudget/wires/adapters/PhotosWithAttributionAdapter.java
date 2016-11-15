package com.porkandlager.travelonbudget.wires.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.porkandlager.travelonbudget.R;
import com.porkandlager.travelonbudget.mvp.PhotoViewer.PhotoViewerActivity;
import com.porkandlager.travelonbudget.wires.Constants;
import com.porkandlager.travelonbudget.wires.models.beans.PhotoWithAttribution;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tista on 11/15/16.
 */

public class PhotosWithAttributionAdapter extends RecyclerView.Adapter<PhotosWithAttributionAdapter.ViewHolder> {

    private List<PhotoWithAttribution> photos;
    private Context context;

    public PhotosWithAttributionAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View morePhotosView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_photo_with_attribution, parent, false);

        return new ViewHolder(morePhotosView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final PhotoWithAttribution photo = photos.get(position);

        Glide.with(context)
                .load(photo.getUrl())
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if(photos == null) {
            return 0;
        }

        return photos.size();
    }

    public void setPhotos(List<PhotoWithAttribution> photos) {
        this.photos = photos;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.row_photos_image) ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(view -> {
                PhotoWithAttribution photo = photos.get(getAdapterPosition());

                Intent intent = new Intent(context, PhotoViewerActivity.class);
                intent.putExtra(Constants.PHOTO_VIEWER_PHOTO_URL,
                        photo.getUrl());

                context.startActivity(intent);
            });
        }

    }

}
