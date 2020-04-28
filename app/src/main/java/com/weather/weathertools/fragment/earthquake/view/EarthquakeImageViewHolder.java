package com.weather.weathertools.fragment.earthquake.view;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.weather.weathertools.R;
import com.weather.weathertools.tools.ImageLoaderProvider;

public class EarthquakeImageViewHolder extends RecyclerView.ViewHolder {


    private RoundedImageView ivPhoto;

    private Context context;

    public EarthquakeImageViewHolder(@NonNull View itemView,Context context) {
        super(itemView);
        this.context = context;
        ivPhoto = itemView.findViewById(R.id.earthquake_image);

    }

    public void setData(String reportImageUri) {
        ImageLoaderProvider.getInstance(context).setPhotoUrl(reportImageUri,ivPhoto);
    }
}
