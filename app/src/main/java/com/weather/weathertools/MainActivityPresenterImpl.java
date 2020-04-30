package com.weather.weathertools;

import android.util.Log;

import com.google.gson.Gson;
import com.weather.weathertools.fragment.json_parser.WeatherBegin;
import com.weather.weathertools.fragment.json_parser.WeatherTwoDaysLocation;
import com.weather.weathertools.tools.WeatherHttpConnection;

import java.util.ArrayList;

public class MainActivityPresenterImpl implements MainActivityPresenter {

    private MainActivityVu mView;

    private ArrayList<String> titleArray,oneWeekArray;

    private int apiUrlIndex;

    private Gson gson;

    private ArrayList<String> cityArray,locationArray;

    private ArrayList<WeatherTwoDaysLocation> locationSelectArray;

    public MainActivityPresenterImpl(MainActivityVu mView) {
        this.mView = mView;
        gson = new Gson();
    }

    @Override
    public void onNavigationButtonClickListener() {
        mView.openDrawerLayout(true);
    }

    @Override
    public void onShowNavigationView(ArrayList<String> broadCastArray) {
        titleArray = new ArrayList<>();
        oneWeekArray = new ArrayList<>();
        for (String title : broadCastArray){
            if (title.contains("2天")){
                titleArray.add(title);
            }
        }
        for (String title : broadCastArray){
            if (title.contains("1週")){
                oneWeekArray.add(title);
            }
        }
        Log.i("Michael","oneWeekArray 長度 : "+oneWeekArray.size());
        mView.showNavigationView();
    }

    @Override
    public void onNavigationItemClickListener(String name, ArrayList<String> apiUrlArray, ArrayList<String> oneWeekArray) {
        Log.i("Michael","oneWeekApiUrl 長度 : "+oneWeekArray.size());
        apiUrlIndex = 0;

        mView.openDrawerLayout(false);
        mView.setTitle(name);
        if (name.equals(mView.get36hrString())){
            String apiUrl = "https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-C0032-001?Authorization=CWB-CF93991C-7A79-4387-8A8B-D5F583B50AEC&format=JSON&locationName=%E5%AE%9C%E8%98%AD%E7%B8%A3,%E8%8A%B1%E8%93%AE%E7%B8%A3,%E8%87%BA%E6%9D%B1%E7%B8%A3,%E6%BE%8E%E6%B9%96%E7%B8%A3,%E9%87%91%E9%96%80%E7%B8%A3,%E9%80%A3%E6%B1%9F%E7%B8%A3,%E8%87%BA%E5%8C%97%E5%B8%82,%E6%96%B0%E5%8C%97%E5%B8%82,%E6%A1%83%E5%9C%92%E5%B8%82,%E8%87%BA%E4%B8%AD%E5%B8%82,%E8%87%BA%E5%8D%97%E5%B8%82,%E9%AB%98%E9%9B%84%E5%B8%82,%E5%9F%BA%E9%9A%86%E5%B8%82,%E6%96%B0%E7%AB%B9%E7%B8%A3,%E6%96%B0%E7%AB%B9%E5%B8%82,%E8%8B%97%E6%A0%97%E7%B8%A3,%E5%BD%B0%E5%8C%96%E7%B8%A3,%E5%8D%97%E6%8A%95%E7%B8%A3,%E9%9B%B2%E6%9E%97%E7%B8%A3,%E5%98%89%E7%BE%A9%E7%B8%A3,%E5%98%89%E7%BE%A9%E5%B8%82,%E5%B1%8F%E6%9D%B1%E7%B8%A3";
            mView.replace36hrFragment(apiUrl);
        }else if (name.contains("2天")){
            for (int i = 0 ; i < titleArray.size() ; i ++){
                if (titleArray.get(i).equals(name)){
                    apiUrlIndex = i;
                    break;
                }
            }
            mView.replace2DaysFragment(apiUrlArray.get(apiUrlIndex));
        }else if (name.contains("1週")){
            for (int i = 0 ; i < this.oneWeekArray.size() ; i ++){
                if (this.oneWeekArray.get(i).equals(name)){
                    apiUrlIndex = i;
                    break;
                }
            }
            mView.replaceOneWeekFragment(oneWeekArray.get(apiUrlIndex));
        }else if (name.contains("一週")){
            mView.replaceOneWeekFragment("https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-D0047-091?Authorization=CWB-CF93991C-7A79-4387-8A8B-D5F583B50AEC&format=JSON&locationName=%E5%AE%9C%E8%98%AD%E7%B8%A3,%E8%8A%B1%E8%93%AE%E7%B8%A3,%E8%87%BA%E6%9D%B1%E7%B8%A3,%E6%BE%8E%E6%B9%96%E7%B8%A3,%E9%87%91%E9%96%80%E7%B8%A3,%E9%80%A3%E6%B1%9F%E7%B8%A3,%E8%87%BA%E5%8C%97%E5%B8%82,%E6%96%B0%E5%8C%97%E5%B8%82,%E6%A1%83%E5%9C%92%E5%B8%82,%E8%87%BA%E4%B8%AD%E5%B8%82,%E8%87%BA%E5%8D%97%E5%B8%82,%E9%AB%98%E9%9B%84%E5%B8%82,%E5%9F%BA%E9%9A%86%E5%B8%82,%E6%96%B0%E7%AB%B9%E7%B8%A3,%E6%96%B0%E7%AB%B9%E5%B8%82,%E8%8B%97%E6%A0%97%E7%B8%A3,%E5%BD%B0%E5%8C%96%E7%B8%A3,%E5%8D%97%E6%8A%95%E7%B8%A3,%E9%9B%B2%E6%9E%97%E7%B8%A3,%E5%98%89%E7%BE%A9%E7%B8%A3,%E5%98%89%E7%BE%A9%E5%B8%82,%E5%B1%8F%E6%9D%B1%E7%B8%A3&elementName=MaxCI,MinT,MaxT,PoP12h,Wx");
        }else if (name.equals("顯著有感地震報告資料-顯著有感地震報告")){
            mView.replaceEarthquakeFragment("https://opendata.cwb.gov.tw/api/v1/rest/datastore/E-A0015-001?Authorization=CWB-CF93991C-7A79-4387-8A8B-D5F583B50AEC&format=JSON&areaName=%E5%AE%9C%E8%98%AD%E7%B8%A3,%E8%8A%B1%E8%93%AE%E7%B8%A3,%E8%87%BA%E6%9D%B1%E7%B8%A3,%E6%BE%8E%E6%B9%96%E7%B8%A3,%E9%87%91%E9%96%80%E7%B8%A3,%E9%80%A3%E6%B1%9F%E7%B8%A3,%E8%87%BA%E5%8C%97%E5%B8%82,%E6%96%B0%E5%8C%97%E5%B8%82,%E6%A1%83%E5%9C%92%E5%B8%82,%E8%87%BA%E4%B8%AD%E5%B8%82,%E8%87%BA%E5%8D%97%E5%B8%82,%E9%AB%98%E9%9B%84%E5%B8%82,%E5%9F%BA%E9%9A%86%E5%B8%82,%E6%96%B0%E7%AB%B9%E7%B8%A3,%E6%96%B0%E7%AB%B9%E5%B8%82,%E8%8B%97%E6%A0%97%E7%B8%A3,%E5%BD%B0%E5%8C%96%E7%B8%A3,%E5%8D%97%E6%8A%95%E7%B8%A3,%E9%9B%B2%E6%9E%97%E7%B8%A3,%E5%98%89%E7%BE%A9%E7%B8%A3,%E5%98%89%E7%BE%A9%E5%B8%82,%E5%B1%8F%E6%9D%B1%E7%B8%A3");
        }
    }

    @Override
    public void onStartToGetApiData(String address, ArrayList<String> oneWeekApiUrlArray) {
        String city = address.substring(0,3);
        final String location = address.substring(3,6);
        if (city.equals("台北市")){
            city = "臺北市";
        }
        Log.i("Michael","拆分後是 : "+city+" , "+location);
        for (int i = 0 ; i < oneWeekArray.size() ; i ++){
            if (oneWeekArray.get(i).contains(city+"未來1週天氣")){
                apiUrlIndex = i;
                break;
            }
        }
        WeatherHttpConnection connection = new WeatherHttpConnection();
        connection.execute(oneWeekApiUrlArray.get(apiUrlIndex));

        connection.setOnConnectionListener(new WeatherHttpConnection.OnConnectionListener() {
            @Override
            public void onSuccessful(String result) {
                Log.i("Michael", "一周天氣取得成功 : "+result);
                WeatherBegin data = gson.fromJson(result,WeatherBegin.class);

                if (data != null){
                    WeatherTwoDaysLocation currentLocation = null;

                    for (WeatherTwoDaysLocation loc : data.getRecords().getLocations().get(0).getLocation()){
                        if (loc.getLocationName().equals(location)){
                            currentLocation = loc;
                            break;
                        }
                    }
                    if (currentLocation != null){
                        mView.setRecyclerView(currentLocation.getWeatherElement());
                    }
                }

            }

            @Override
            public void onFailure(String errorCode) {
                Log.i("Michael","取得首頁資料錯誤 : "+errorCode);
            }
        });

        final String locationApiUrl = "https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-D0047-091?Authorization=CWB-CF93991C-7A79-4387-8A8B-D5F583B50AEC&format=JSON&locationName=%E5%AE%9C%E8%98%AD%E7%B8%A3,%E8%8A%B1%E8%93%AE%E7%B8%A3,%E8%87%BA%E6%9D%B1%E7%B8%A3,%E6%BE%8E%E6%B9%96%E7%B8%A3,%E9%87%91%E9%96%80%E7%B8%A3,%E9%80%A3%E6%B1%9F%E7%B8%A3,%E8%87%BA%E5%8C%97%E5%B8%82,%E6%96%B0%E5%8C%97%E5%B8%82,%E6%A1%83%E5%9C%92%E5%B8%82,%E8%87%BA%E4%B8%AD%E5%B8%82,%E8%87%BA%E5%8D%97%E5%B8%82,%E9%AB%98%E9%9B%84%E5%B8%82,%E5%9F%BA%E9%9A%86%E5%B8%82,%E6%96%B0%E7%AB%B9%E7%B8%A3,%E6%96%B0%E7%AB%B9%E5%B8%82,%E8%8B%97%E6%A0%97%E7%B8%A3,%E5%BD%B0%E5%8C%96%E7%B8%A3,%E5%8D%97%E6%8A%95%E7%B8%A3,%E9%9B%B2%E6%9E%97%E7%B8%A3,%E5%98%89%E7%BE%A9%E7%B8%A3,%E5%98%89%E7%BE%A9%E5%B8%82,%E5%B1%8F%E6%9D%B1%E7%B8%A3&elementName=MaxCI,MinT,MaxT,PoP12h,Wx";
        connection = new WeatherHttpConnection();
        connection.execute(locationApiUrl);
        connection.setOnConnectionListener(new WeatherHttpConnection.OnConnectionListener() {
            @Override
            public void onSuccessful(String result) {
                Log.i("Michael","Get Data : "+result);
                WeatherBegin data = gson.fromJson(result,WeatherBegin.class);
                if (data != null){

                    cityArray = new ArrayList<>();
                    for (WeatherTwoDaysLocation location : data.getRecords().getLocations().get(0).getLocation()) {
                        cityArray.add(location.getLocationName());
                    }
                }
            }
            @Override
            public void onFailure(String errorCode) {
                Log.i("Michael","取得地區資料錯誤 : "+errorCode);
            }
        });



    }

    @Override
    public void onNavigationBackButtonClickListener() {
        mView.showMainActivity();
    }

    @Override
    public void onLocationButtonClickListener() {
        if (cityArray != null){
            mView.showLocationDialog(cityArray);
        }

    }

    @Override
    public void onSpinnerItemClickListener(String city, ArrayList<String> oneWeekApiUrlArray) {
        for (int i = 0 ; i < oneWeekArray.size() ; i ++){
            if (oneWeekArray.get(i).contains(city+"未來1週天氣")){
                apiUrlIndex = i;
                break;
            }
        }
        WeatherHttpConnection connection = new WeatherHttpConnection();
        connection.execute(oneWeekApiUrlArray.get(apiUrlIndex));
        connection.setOnConnectionListener(new WeatherHttpConnection.OnConnectionListener() {
            @Override
            public void onSuccessful(String result) {
                WeatherBegin data = gson.fromJson(result,WeatherBegin.class);

                if (data != null){
                    locationArray = new ArrayList<>();
                    locationSelectArray = data.getRecords().getLocations().get(0).getLocation();
                    for (WeatherTwoDaysLocation loc : data.getRecords().getLocations().get(0).getLocation()){

                        locationArray.add(loc.getLocationName());
                    }
                    if (locationArray.size() != 0){
                        mView.setDialogRecyclerView(locationArray);
                    }
                }
            }

            @Override
            public void onFailure(String errorCode) {
                Log.i("Michael","錯誤 : "+errorCode);

                mView.showErrorCodeDialog(errorCode);


            }
        });
    }

    @Override
    public void onDialogItemClickListener(String location) {

        mView.setLocationTitle(location);

        WeatherTwoDaysLocation locationData = null;

        for (WeatherTwoDaysLocation data : locationSelectArray){
            if (location.equals(data.getLocationName())){
                locationData = data;
                break;
            }
        }
        if (locationData != null){
            mView.setRecyclerView(locationData.getWeatherElement());
        }

    }
}
