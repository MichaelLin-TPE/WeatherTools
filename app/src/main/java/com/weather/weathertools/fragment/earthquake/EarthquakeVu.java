package com.weather.weathertools.fragment.earthquake;

import com.weather.weathertools.fragment.json_parser.earthquake.EarthquakeObject;

public interface EarthquakeVu {
    void setRecyclerView(EarthquakeObject data);

    void showTitle(String reportContent);
}
