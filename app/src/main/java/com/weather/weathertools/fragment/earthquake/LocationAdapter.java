package com.weather.weathertools.fragment.earthquake;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.weather.weathertools.R;
import com.weather.weathertools.fragment.json_parser.earthquake.EarthquakeEqStation;
import com.weather.weathertools.tools.ImageLoaderProvider;

import java.util.ArrayList;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {

    private ArrayList<EarthquakeEqStation> dataArray;

    private Context context;

    public LocationAdapter(ArrayList<EarthquakeEqStation> dataArray, Context context) {
        this.dataArray = dataArray;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return  new ViewHolder(LayoutInflater.from(context).inflate(R.layout.location_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        EarthquakeEqStation data = dataArray.get(position);

        holder.tvTitle.setText(data.getStationName());

        ImageLoaderProvider.getInstance(context).setPhotoUrl(data.getWaveImageUri(),holder.ivPhoto);
    }

    @Override
    public int getItemCount() {
        return dataArray == null ? 0 : dataArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;

        private RoundedImageView ivPhoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.location_item_title);
            ivPhoto = itemView.findViewById(R.id.location_item_photo);
        }
    }
}
