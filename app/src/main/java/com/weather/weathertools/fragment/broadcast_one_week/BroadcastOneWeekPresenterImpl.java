package com.weather.weathertools.fragment.broadcast_one_week;

import android.util.Log;

import com.google.gson.Gson;
import com.weather.weathertools.fragment.json_parser.WeatherBegin;
import com.weather.weathertools.tools.WeatherHttpConnection;

public class BroadcastOneWeekPresenterImpl implements BroadcastOneWeekPresenter {

    private BroadcastOneWeekVu mView;

    private Gson gson;

    public BroadcastOneWeekPresenterImpl(BroadcastOneWeekVu mView) {
        this.mView = mView;
        gson = new Gson();
    }

    @Override
    public void onGetApiData(String apiUrl) {
        WeatherHttpConnection connection = new WeatherHttpConnection();
        connection.execute(apiUrl);
        connection.setOnConnectionListener(new WeatherHttpConnection.OnConnectionListener() {
            @Override
            public void onSuccessful(String result) {
                Log.i("Michael","Get Data : "+result);
                WeatherBegin data = gson.fromJson(result,WeatherBegin.class);
                if (data != null){
                    mView.setRecyclerView(data.getRecords().getLocations().get(0).getLocation());
                }
            }

            @Override
            public void onFailure(String errorCode) {
                Log.i("Michael",errorCode);
                mView.showErrorCodeDialog(errorCode);
            }
        });
    }
}
