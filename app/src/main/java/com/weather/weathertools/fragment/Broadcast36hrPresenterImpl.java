package com.weather.weathertools.fragment;

import android.util.Log;

import com.google.gson.Gson;
import com.weather.weathertools.fragment.json_parser.WeatherLocation;
import com.weather.weathertools.fragment.json_parser.WeatherObject;
import com.weather.weathertools.tools.WeatherHttpConnection;

import java.util.ArrayList;

public class Broadcast36hrPresenterImpl implements Broadcast36hrPresenter {

    private Broadcast36hrVu mView;

    private Gson gson;

    public Broadcast36hrPresenterImpl(Broadcast36hrVu mView) {
        this.mView = mView;
        gson = new Gson();
    }

    @Override
    public void onStartGetData(String apiUrl) {
        WeatherHttpConnection connection = new WeatherHttpConnection();
        connection.execute(apiUrl);
        connection.setOnConnectionListener(new WeatherHttpConnection.OnConnectionListener() {
            @Override
            public void onSuccessful(String result) {
                Log.i("Michael","Get success : "+result);
                WeatherObject object = gson.fromJson(result,WeatherObject.class);
                ArrayList<WeatherLocation> dataArray = object.getRecords().getLocations();
                mView.setRecyclerView(dataArray);
            }

            @Override
            public void onFailure(String errorCode) {
                Log.i("Michael","錯誤 : "+errorCode);
            }
        });
    }
}
