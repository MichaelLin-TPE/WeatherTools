package com.weather.weathertools.fragment.earthquake;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.weather.weathertools.R;
import com.weather.weathertools.fragment.earthquake.view_presenter.ViewPresenter;
import com.weather.weathertools.fragment.earthquake.view_presenter.ViewPresenterImpl;
import com.weather.weathertools.fragment.json_parser.earthquake.EarthquakeObject;


public class EarthquakeFragment extends Fragment implements EarthquakeVu {

    private EarthquakePresenter presenter;

    private String apiUrl;

    private Context context;

    private RecyclerView recyclerView;

    private TextView tvTitle;

    private ViewPresenter viewPresenter;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public static EarthquakeFragment newInstance(String apiUrl) {
        EarthquakeFragment fragment = new EarthquakeFragment();
        Bundle args = new Bundle();
        args.putString("api_url", apiUrl);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
        if (getArguments() != null) {
            apiUrl = getArguments().getString("api_url");
        }
    }

    private void initPresenter() {
        presenter = new EarthquakePresenterImpl(this);
        viewPresenter = new ViewPresenterImpl();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_earthquake, container, false);
        initView(view);
        // Inflate the layout for this fragment
        return view;
    }

    private void initView(View view) {
        tvTitle = view.findViewById(R.id.earthquake_fg_title);
        recyclerView = view.findViewById(R.id.earthquake_fg_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.onStartGetApiData(apiUrl);
    }

    @Override
    public void setRecyclerView(EarthquakeObject data) {
        viewPresenter.setData(data);

        EarthquakeAdapter adapter = new EarthquakeAdapter(context,viewPresenter);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showTitle(String reportContent) {
        tvTitle.setText(reportContent);
    }
}
