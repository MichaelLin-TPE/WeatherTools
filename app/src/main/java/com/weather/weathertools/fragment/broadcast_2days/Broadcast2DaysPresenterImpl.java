package com.weather.weathertools.fragment.broadcast_2days;

import android.util.Log;

import com.google.gson.Gson;
import com.weather.weathertools.fragment.json_parser.WeatherBegin;
import com.weather.weathertools.tools.WeatherHttpConnection;

public class Broadcast2DaysPresenterImpl implements Broadcast2DaysPresenter {
    private Broadcast2DaysVu mView;

    private Gson gson;

    public Broadcast2DaysPresenterImpl(Broadcast2DaysVu mView) {
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
                WeatherBegin data = gson.fromJson(result,WeatherBegin.class);
                if (data != null){
                    mView.setRecyclerView(data.getRecords().getLocations().get(0).getLocation());
                }
            }

            @Override
            public void onFailure(String errorCode) {
                Log.i("Michael","錯誤 : "+errorCode);
            }
        });
    }
}
