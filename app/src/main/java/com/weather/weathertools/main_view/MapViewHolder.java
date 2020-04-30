package com.weather.weathertools.main_view;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.weather.weathertools.R;

public class MapViewHolder extends RecyclerView.ViewHolder implements OnMapReadyCallback {

    private MapView mapView;
    
    private GoogleMap map;
    
    private Context context;

    private double latitude,longitude;

    public MapViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
        mapView = itemView.findViewById(R.id.basic_map);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;


        LatLng location = new LatLng(latitude, longitude);
        map.addMarker(new MarkerOptions().position(location).title("目前位置"));

        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true);
            map.getUiSettings().setMyLocationButtonEnabled(true);
        } else if (context!= null){
            ActivityCompat.requestPermissions((Activity) context,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
            },0);
        }
        map.getUiSettings().setZoomControlsEnabled(true);  // 右下角的放大縮小功能
        map.getUiSettings().setCompassEnabled(true);       // 左上角的指南針，要兩指旋轉才會出現
        map.getUiSettings().setMapToolbarEnabled(true);    // 右下角的導覽及開啟 Google Map功能

        map.moveCamera(CameraUpdateFactory.newLatLng(location));
        map.animateCamera(CameraUpdateFactory.zoomTo(16));
    }

    public void setData(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }

    }
}
