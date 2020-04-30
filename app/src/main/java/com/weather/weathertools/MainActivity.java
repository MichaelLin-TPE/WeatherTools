package com.weather.weathertools;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.weather.weathertools.fragment.broadcast_2days.Broadcast2DaysFragment;
import com.weather.weathertools.fragment.broadcast_2days.OneWeekItemAdapter;
import com.weather.weathertools.fragment.broadcast_36hrs.Broadcast36hrFragment;
import com.weather.weathertools.fragment.broadcast_one_week.BroadcastOneWeekFragment;
import com.weather.weathertools.fragment.earthquake.EarthquakeFragment;
import com.weather.weathertools.fragment.json_parser.WeatherTwoDaysElement;
import com.weather.weathertools.navigation_view.NavigationPresenter;
import com.weather.weathertools.navigation_view.NavigationPresenterImpl;
import com.weather.weathertools.navigation_view.NavigationViewAdapter;
import com.weather.weathertools.navigation_view.view.BroadCastAdapter;
import com.weather.weathertools.status_presenter.StatusPresenter;
import com.weather.weathertools.status_presenter.StatusPresenterImpl;
import com.weather.weathertools.tools.ItemSpace;
import com.weather.weathertools.tools.TitleProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements MainActivityVu, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private DrawerLayout drawerLayout;

    private RecyclerView navigationRecyclerView;

    private MainActivityPresenter presenter;

    private NavigationPresenter navigationPresenter;

    private FragmentManager fragmentManager;

    private Toolbar toolbar;

    private GoogleApiClient googleApiClient;
    private Location location;

    private int permission;

    private OneWeekItemAdapter adapter;

    private TextView tvLocation,tvBack;

    private RecyclerView recyclerView;

    private static final int REQUEST_LOCATION = 1;

    private FrameLayout frameLayout;

    private static String[] PERMISSION_LOCATION = {"android.permission.ACCESS_FINE_LOCATION"
                                                 ,"android.permission.ACCESS_COARSE_LOCATION"
                                                 ,"android.permission.ACCESS_BACKGROUND_LOCATION"};
    private RecyclerView rvDialog;

    private AlertDialog dialog;

    private StatusPresenter statusPresenter;

    private String city;

    private ProgressBar progressBar;

    private MainActivityAdapter activityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        initPresenter();
        initView();

        presenter.onShowNavigationView(TitleProvider.getInstance(MainActivity.this).getBroadCastArray());

        verifyLocationPermissions(this);


        //startService
//        Log.i("Michael","開始 intent Srvices");
//        Intent it = new Intent(this,LocationService.class);
//        startService(it);

    }
    //取得權限
    private void verifyLocationPermissions(MainActivity mainActivity) {
        try{
            permission = ActivityCompat.checkSelfPermission(mainActivity,
                    "android.permission.ACCESS_FINE_LOCATION");

            if (permission != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(mainActivity,PERMISSION_LOCATION,REQUEST_LOCATION);
            }else {
                buildGoogleApiClient();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //取得權限
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case REQUEST_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    NotificationManagerCompat manager = NotificationManagerCompat.from(MainActivity.this);
                    boolean isEnable = manager.areNotificationsEnabled();
                    if (!isEnable){
                        AlertDialog dialog = new AlertDialog.Builder(this)
                                .setTitle(getString(R.string.permission))
                                .setMessage(getString(R.string.is_open_notification))
                                .setPositiveButton(getString(R.string.confirm), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                            //这种方案适用于 API 26, 即8.0（含8.0）以上可以用
                                            Intent intent = new Intent();
                                            intent.setAction(Settings.ACTION_APPLICATION_SETTINGS);
                                            intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
                                            intent.putExtra(Settings.EXTRA_CHANNEL_ID, getApplicationInfo().uid);
                                            startActivity(intent);
                                        } else {
                                            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {
                                                toSystemConfig();
                                            } else {
                                                try {
                                                    toApplicationInfo();
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                    toSystemConfig();
                                                }
                                            }
                                        }
                                    }
                                }).setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                }).create();
                        dialog.show();
                    }else {
                        buildGoogleApiClient();
                    }
                }else {
                    ActivityCompat.requestPermissions(MainActivity.this, PERMISSION_LOCATION, REQUEST_LOCATION);
                }
                break;
        }

    }

    private void toApplicationInfo() {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        localIntent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        localIntent.setData(Uri.fromParts("package", getPackageName(), null));
        startActivity(localIntent);
    }

    private void toSystemConfig() {
        try {
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (googleApiClient.isConnected()){
            googleApiClient.disconnect();
        }
    }

    private void initView() {
        progressBar = findViewById(R.id.main_progress);
        frameLayout = findViewById(R.id.main_frame_layout);
        tvBack = findViewById(R.id.main_navi_back);
        recyclerView = findViewById(R.id.main_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tvLocation = findViewById(R.id.main_address);
        drawerLayout = findViewById(R.id.main_drawer_layout);
        navigationRecyclerView = findViewById(R.id.navigation_recycler_view);
        navigationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        toolbar = findViewById(R.id.main_toolbar);
        tvLocation.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onNavigationButtonClickListener();
            }
        });
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onNavigationBackButtonClickListener();
            }
        });
        tvLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLocationButtonClickListener();
            }
        });
    }

    private void initPresenter() {
        presenter = new MainActivityPresenterImpl(this);
        statusPresenter = new StatusPresenterImpl();
        navigationPresenter = new NavigationPresenterImpl();
    }

    @Override
    public void openDrawerLayout(boolean isOpen) {
        if (isOpen){
            drawerLayout.openDrawer(GravityCompat.START);
        }else {
            drawerLayout.closeDrawers();
        }
    }

    @Override
    public void showNavigationView() {
       navigationPresenter.setBroadCastData(TitleProvider.getInstance(this).getBroadCastArray());
       NavigationViewAdapter adapter = new NavigationViewAdapter(navigationPresenter,this);
       navigationRecyclerView.setAdapter(adapter);
       adapter.setOnNavigationItemClickListener(new BroadCastAdapter.OnNavigationItemClickListener() {
           @Override
           public void onClick(String name) {
               Log.i("Michael","點擊了 : "+name);
               presenter.onNavigationItemClickListener(name,TitleProvider.getInstance(MainActivity.this).get3DaysApiUrlArray(),TitleProvider.getInstance(MainActivity.this).getOneWeekApiUrlArray());
           }
       });
    }

    @Override
    public String get36hrString() {
        return getString(R.string.broad_title_1);
    }

    @Override
    public void replace36hrFragment(String apiUrl) {
        recyclerView.setVisibility(View.GONE);
        frameLayout.setVisibility(View.VISIBLE);
        fragmentManager.beginTransaction().replace(R.id.main_frame_layout,Broadcast36hrFragment.newInstance(apiUrl)).commit();
    }

    @Override
    public void setTitle(String name) {
        tvLocation.setText(name);
    }

    @Override
    public void replace2DaysFragment(String apiUrl) {
        recyclerView.setVisibility(View.GONE);
        frameLayout.setVisibility(View.VISIBLE);
        fragmentManager.beginTransaction().replace(R.id.main_frame_layout, Broadcast2DaysFragment.newInstance(apiUrl)).commit();
    }

    @Override
    public void replaceOneWeekFragment(String apiUrl) {
        recyclerView.setVisibility(View.GONE);
        frameLayout.setVisibility(View.VISIBLE);
        fragmentManager.beginTransaction().replace(R.id.main_frame_layout, BroadcastOneWeekFragment.newInstance(apiUrl)).commit();
    }

    @Override
    public void setRecyclerView(ArrayList<WeatherTwoDaysElement> dataArray, double latitude, double longitude) {

        if (activityAdapter != null){
            statusPresenter.setData(dataArray);
            statusPresenter.setLocation(latitude,longitude);
            activityAdapter.notifyDataSetChanged();
            return;
        }
        statusPresenter.setData(dataArray);
        statusPresenter.setLocation(latitude,longitude);
        activityAdapter = new MainActivityAdapter(statusPresenter,this);
        int space = 40;
        recyclerView.addItemDecoration(new ItemSpace(space));
        recyclerView.setAdapter(activityAdapter);

//        adapter = new OneWeekItemAdapter(dataArray,this);
//        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showMainActivity() {

        drawerLayout.closeDrawers();
        frameLayout.setVisibility(View.GONE);
        tvLocation.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
        tvLocation.setText(getString(R.string.location_weather));
    }

    @Override
    public void replaceEarthquakeFragment(String apiUrl) {
        tvLocation.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        frameLayout.setVisibility(View.VISIBLE);
        fragmentManager.beginTransaction().replace(R.id.main_frame_layout, EarthquakeFragment.newInstance(apiUrl)).commit();
    }

    @Override
    public void showLocationDialog(final ArrayList<String> locationArray) {
        View view = View.inflate(this,R.layout.location_dialog_view,null);
        rvDialog = view.findViewById(R.id.location_dialog_recycler_view);
        rvDialog.setLayoutManager(new GridLayoutManager(this,4));
        Spinner spinner = view.findViewById(R.id.location_dialog_spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,locationArray);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                presenter.onSpinnerItemClickListener(locationArray.get(position),TitleProvider.getInstance(MainActivity.this).getOneWeekApiUrlArray());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //nothing to do
            }
        });

        dialog = new AlertDialog.Builder(this)
                .setView(view).create();
        dialog.show();



    }

    @Override
    public void setDialogRecyclerView(String city, ArrayList<String> locationArray) {
        this.city = city;
        final DialogAdapter adapter = new DialogAdapter(locationArray,this);
        rvDialog.setAdapter(adapter);
        adapter.setOnDialogItemClickListener(new DialogAdapter.OnDialogItemClickListener() {
            @Override
            public void onClick(String location) {
                adapter.notifyDataSetChanged();
                Log.i("Michael","點擊了 : "+location);
                presenter.onDialogItemClickListener(location);
                dialog.dismiss();

            }
        });
    }

    @Override
    public void setLocationTitle(String location) {
        tvLocation.setText(String.format(Locale.getDefault(),"位置 : %s%s",city,location));
    }

    @Override
    public void showErrorCodeDialog(String errorCode) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(getString(R.string.information_fail))
                .setMessage(getString(R.string.get_error_code)+errorCode)
                .setPositiveButton(getString(R.string.confirm), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        
                    }
                }).setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create();
        dialog.show();
    }

    @Override
    public void setBackground(String weatherStatus) {
        Log.i("Michael","天氣狀態 : "+weatherStatus);
        if (weatherStatus.contains("晴")){
            drawerLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.sun_background));
        }else if (weatherStatus.contains("雲")){
            drawerLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.cloudy_background));
        }else if (weatherStatus.contains("雨")){
            drawerLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.rain_background));
        }
    }

    @Override
    public void hideProgress(boolean isShow) {
        progressBar.setVisibility(isShow ? View.GONE : View.VISIBLE);
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (location != null){
            Log.i("Michael",String.format("%s: %f", "經度 : ", location.getLatitude()));
            Log.i("Michael",String.format("%s: %f", "緯度 : ", location.getLongitude()));
            String address = getAddressByLocation(location);
            Log.i("Michael","目前所在的地址 : "+address);
            int strIndex = address.indexOf("灣");
            address = getAddressByLocation(location).substring(strIndex+1,strIndex+7);

            Log.i("Michael","擷取後的字串 : "+address);
            tvLocation.setText(String.format(Locale.getDefault(),"位置 : %s",address));
            presenter.onStartToGetApiData(address,TitleProvider.getInstance(this).getOneWeekApiUrlArray(),location.getLatitude(),location.getLongitude());

        }else {
            Log.i("Michael","目前偵測不到位置");
        }
    }

    private String getAddressByLocation(Location location) {
        String address = "";
        try{
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            Geocoder gc = new Geocoder(this, Locale.TRADITIONAL_CHINESE);
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
        Log.i("Michael","Connection suspended");
        googleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i("Michael","偵測位置失敗 : "+connectionResult.getErrorCode());
    }


}
