package com.weather.weathertools.fragment.earthquake;

import android.util.Log;

import com.google.gson.Gson;
import com.weather.weathertools.fragment.json_parser.earthquake.EarthquakeObject;
import com.weather.weathertools.tools.WeatherHttpConnection;

public class EarthquakePresenterImpl implements EarthquakePresenter {

    private EarthquakeVu mView;

    private Gson gson;

    public EarthquakePresenterImpl(EarthquakeVu mView) {
        this.mView = mView;
        gson = new Gson();
    }

    @Override
    public void onStartGetApiData(String apiUrl) {
        WeatherHttpConnection connection = new WeatherHttpConnection();
        connection.execute(apiUrl);
        connection.setOnConnectionListener(new WeatherHttpConnection.OnConnectionListener() {
            @Override
            public void onSuccessful(String result) {
                Log.i("Michael","Get Earthquake : "+result);
                EarthquakeObject data = gson.fromJson(result,EarthquakeObject.class);
                if (data != null){
                    mView.showTitle(data.getRecords().getEarthquake().get(0).getReportContent());
                    mView.setRecyclerView(data);
                }
            }

            @Override
            public void onFailure(String errorCode) {
                Log.i("Michael","錯誤 : "+errorCode);
            }
        });
    }
}
