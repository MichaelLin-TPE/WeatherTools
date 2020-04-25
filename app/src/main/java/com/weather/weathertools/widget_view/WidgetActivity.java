package com.weather.weathertools.widget_view;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;
import com.weather.weathertools.MainActivity;
import com.weather.weathertools.R;
import com.weather.weathertools.fragment.json_parser.WeatherBegin;
import com.weather.weathertools.fragment.json_parser.WeatherTwoDaysLocation;
import com.weather.weathertools.fragment.json_parser.thirty_hours.WeatherLocation;
import com.weather.weathertools.tools.TitleProvider;
import com.weather.weathertools.tools.WeatherHttpConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WidgetActivity extends AppWidgetProvider implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private PendingIntent pendingIntent;

    private RemoteViews myRemoteViews;

    private ComponentName myComponentName;

    private AppWidgetManager myAppWidgetManager;

    private GoogleApiClient googleApiClient;

    private Location location;

    private Context context;

    private Gson gson;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        gson = new Gson();
        this.context = context;

        Log.i("Michael", "onUpdate");
        //點選桌面元件時進入主程式入口
        Intent intent = new Intent(context, MainActivity.class);
        pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        //RemoteViews類描述了一個View物件能夠顯示在其他程序中，可以融合layout資原始檔實現佈局。
        //雖然該類在android.widget.RemoteViews而不是appWidget下面,但在Android Widgets開發中會經常用到它，
        //主要是可以跨程序呼叫(appWidget由一個服務宿主來統一執行的)。
        myRemoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_view);

        //myRemoteViews.setImageViewResource(R.id.imageView, R.drawable.png1);//設定佈局控制元件的屬性（要特別注意）

        buildGoogleApiClient();



    }

    private void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();
    }

    /**
     * 當App Widget從宿主中刪除時被呼叫。
     */
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        System.out.println("onDeleted");
        super.onDeleted(context, appWidgetIds);
    }

    /**
     * 當一個App Widget例項第一次建立時被呼叫。
     * 比如，如果使用者新增兩個App Widget例項，只在第一次被呼叫。
     * 如果你需要開啟一個新的資料庫或者執行其他對於所有的App Widget例項只需要發生一次的設定，
     * 那麼這裡是完成這個工作的好地方。
     */
    @Override
    public void onEnabled(Context context) {
        System.out.println("onEnabled");
        super.onEnabled(context);
    }

    /**
     * 當你的App Widget的最後一個例項被從宿主中刪除時被呼叫。你應該在onEnabled(Context)中做一些清理工作，比如刪除一個臨時的資料庫
     */
    @Override
    public void onDisabled(Context context) {
        System.out.println("onDisabled");
        super.onDisabled(context);
    }

    /**
     * 接收到每個廣播時都會被呼叫，而且在上面的回撥函式之前。
     * 你通常不需要實現這個方法，因為預設的AppWidgetProvider實現過濾所有App Widget廣播並恰當的呼叫上述方法。
     * 注意: 在Android 1.5中，有一個已知問題，onDeleted()方法在呼叫時不被呼叫。
     * 為了規避這個問題，你可以像Group post中描述的那樣實現onReceive()來接收這個onDeleted()回撥。
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("onReceive");
        super.onReceive(context, intent);
    }

    @Override
    public void onConnected(Bundle bundle) {
        location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (location != null){
            Log.i("Michael",String.format("%s: %f", "經度 : ", location.getLatitude()));
            Log.i("Michael",String.format("%s: %f", "緯度 : ", location.getLongitude()));
            String address = getAddressByLocation(location);
            Log.i("Michael","目前所在的地址 : "+address);
            int strIndex = address.indexOf("灣");
            address = getAddressByLocation(location).substring(strIndex+1,strIndex+7);
            String city = address.substring(0,3);
            final String location = address.substring(3,6);
            if (city.equals("台北市")){
                city = "臺北市";
            }

            ArrayList<String> titleArray = TitleProvider.getInstance(context).getBroadCastArray();
            ArrayList<String> apiUrlArray = TitleProvider.getInstance(context).getOneWeekApiUrlArray();
            String apiUrl = null;
            ArrayList<String> allOneWeekArray = new ArrayList<>();
            for (String title : titleArray){
                if (title.contains("1週")){
                    allOneWeekArray.add(title);
                }
            }

            for (int i = 0 ; i < allOneWeekArray.size() ; i ++){
                if (allOneWeekArray.get(i).contains(city+"未來1週天氣")){
                    apiUrl =apiUrlArray.get(i);
                    break;
                }
            }
            if (apiUrl != null){
                WeatherHttpConnection connection = new WeatherHttpConnection();
                connection.execute(apiUrl);
                connection.setOnConnectionListener(new WeatherHttpConnection.OnConnectionListener() {
                    @Override
                    public void onSuccessful(String result) {
                        Log.i("Michael","Widget Get Data : "+result);
                        WeatherBegin data = gson.fromJson(result,WeatherBegin.class);
                        WeatherTwoDaysLocation loc = null;
                        for (WeatherTwoDaysLocation locationWeather : data.getRecords().getLocations().get(0).getLocation()){
                            if (locationWeather.getLocationName().equals(location)){
                                loc = locationWeather;
                            }
                        }

                        if (loc != null){
                            myRemoteViews.setTextViewText(R.id.widget_location,location);
                            String weatherState = null;
                            String temp = null;
                            for (int i = 0 ; i < loc.getWeatherElement().size() ; i ++){
                                if (loc.getWeatherElement().get(i).getElemtNmae().equals("MaxT")){
                                    temp = loc.getWeatherElement().get(i).getTime().get(0).getElementValue().get(0).getValue()+"°C";
                                }
                                if (loc.getWeatherElement().get(i).getElemtNmae().equals("Wx")){
                                    weatherState = loc.getWeatherElement().get(i).getTime().get(0).getElementValue().get(0).getValue();
                                }
                                if (temp != null && weatherState != null){
                                    break;
                                }
                            }
                            if (weatherState != null && temp != null){

                                myRemoteViews.setTextViewText(R.id.widget_temp,temp);

                                if (weatherState.contains("雨")){
                                    myRemoteViews.setImageViewResource(R.id.widget_icon,R.drawable.rain);
                                }else if (weatherState.contains("陰")){
                                    myRemoteViews.setImageViewResource(R.id.widget_icon,R.drawable.cloudy);
                                }else if (weatherState.contains("晴")){
                                    myRemoteViews.setImageViewResource(R.id.widget_icon,R.drawable.sun);
                                }else {
                                    myRemoteViews.setImageViewResource(R.id.widget_icon,R.drawable.cloudy);
                                }
                                myRemoteViews.setOnClickPendingIntent(R.id.widget_click_area, pendingIntent);
                                myComponentName = new ComponentName(context, WidgetActivity.class);
                                //負責管理AppWidget，向AppwidgetProvider傳送通知。提供了更新AppWidget狀態，獲取已經安裝的Appwidget提供資訊和其他的相關狀態
                                myAppWidgetManager = AppWidgetManager.getInstance(context);
                                myAppWidgetManager.updateAppWidget(myComponentName, myRemoteViews);

                            }else {
                                Log.i("Michael","資料 == null");
                            }

                        }else {
                            Log.i("Michael","loc == null");
                        }

                    }

                    @Override
                    public void onFailure(String errorCode) {
                        Log.i("Michael","widget Data fail : "+errorCode);
                    }
                });
            }else {
                Log.i("Michael","apiUrl == null");
            }










        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    private String getAddressByLocation(Location location) {
        String address = "";
        try{
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            Geocoder gc = new Geocoder(context, Locale.TRADITIONAL_CHINESE);
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
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
