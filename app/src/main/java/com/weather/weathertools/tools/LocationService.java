package com.weather.weathertools.tools;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.List;
import java.util.Locale;

public class LocationService extends Service implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient googleApiClient;

    private Handler handler = new Handler();

    private Runnable showTime = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this, 5000);
            Log.i("Michael", "開始取得GPS位置 每10秒取一次");

            buildGoogleApiClient();


        }
    };

    private void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        Log.i("Michael", "Service onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        handler.post(showTime);
        Log.i("Michael", "onStartCommand");

        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(showTime);
        Log.i("Michael", "service onDestroy");
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (googleApiClient != null) {
            final FusedLocationProviderClient client = new FusedLocationProviderClient(getApplicationContext());
            final Task<Location> currentLocationTask = client.getLastLocation();
            currentLocationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
                @SuppressLint("MissingPermission")
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        String address = getCurrentLocation(location);
                        Log.i("Michael", "取得地址 : " + address);
                    } else {
                        Log.i("Michael", "location == null 即將更新 location");
                        LocationManager locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
                        LocationListener locationListener = new LocationListener() {
                            @Override
                            public void onLocationChanged(Location location) {
                                Log.i("Michael","onLocationChanged");
                                makeUseOfNewLocation(location);
                            }

                            @Override
                            public void onStatusChanged(String provider, int status, Bundle extras) {

                            }

                            @Override
                            public void onProviderEnabled(String provider) {

                            }

                            @Override
                            public void onProviderDisabled(String provider) {

                            }
                        };
                        // Register the listener with the Location Manager to receive location updates
                        if (locationManager != null){

                            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                                    && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                // TODO: Consider calling
                                //    ActivityCompat#requestPermissions
                                // here to request the missing permissions, and then overriding
                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                //                                          int[] grantResults)
                                // to handle the case where the user grants the permission. See the documentation
                                // for ActivityCompat#requestPermissions for more details.
                                Log.i("Michael","Not Enough Permission");
                                Toast.makeText(getApplicationContext(), "Not Enough Permission", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            Log.i("Michael","requestLocationUpdates");
                            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
                            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
                        }else {
                            Log.i("Michael","locationManager == null");
                        }

                    }

                }
            });
        }

    }

    private void makeUseOfNewLocation(Location location) {
        String address = getCurrentLocation(location);
        Log.i("Michael","更新過後的地址 : "+address);

    }

    private String getCurrentLocation(Location location) {
        String address = "";
        try{
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            Geocoder gc = new Geocoder(getApplicationContext(), Locale.TRADITIONAL_CHINESE);
            List<Address> addressArray = gc.getFromLocation(latitude,longitude,1);
            if (!Geocoder.isPresent()){
                address = "Sorry! 請開啟你的定位系統,感恩的心~*";
            }
            address = addressArray.get(0).getAddressLine(0);
        }catch (Exception e){
            e.printStackTrace();
        }
        return address;
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
