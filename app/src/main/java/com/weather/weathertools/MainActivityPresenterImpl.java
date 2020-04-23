package com.weather.weathertools;

public class MainActivityPresenterImpl implements MainActivityPresenter {

    private MainActivityVu mView;

    public MainActivityPresenterImpl(MainActivityVu mView) {
        this.mView = mView;
    }

    @Override
    public void onNavigationButtonClickListener() {
        mView.openDrawerLayout(true);
    }

    @Override
    public void onShowNavigationView() {
        mView.showNavigationView();
    }

    @Override
    public void onNavigationItemClickListener(String name) {
        mView.openDrawerLayout(false);
        if (name.equals(mView.get36hrString())){
            String apiUrl = "https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-C0032-001?Authorization=CWB-CF93991C-7A79-4387-8A8B-D5F583B50AEC&format=JSON&locationName=%E5%AE%9C%E8%98%AD%E7%B8%A3,%E8%8A%B1%E8%93%AE%E7%B8%A3,%E8%87%BA%E6%9D%B1%E7%B8%A3,%E6%BE%8E%E6%B9%96%E7%B8%A3,%E9%87%91%E9%96%80%E7%B8%A3,%E9%80%A3%E6%B1%9F%E7%B8%A3,%E8%87%BA%E5%8C%97%E5%B8%82,%E6%96%B0%E5%8C%97%E5%B8%82,%E6%A1%83%E5%9C%92%E5%B8%82,%E8%87%BA%E4%B8%AD%E5%B8%82,%E8%87%BA%E5%8D%97%E5%B8%82,%E9%AB%98%E9%9B%84%E5%B8%82,%E5%9F%BA%E9%9A%86%E5%B8%82,%E6%96%B0%E7%AB%B9%E7%B8%A3,%E6%96%B0%E7%AB%B9%E5%B8%82,%E8%8B%97%E6%A0%97%E7%B8%A3,%E5%BD%B0%E5%8C%96%E7%B8%A3,%E5%8D%97%E6%8A%95%E7%B8%A3,%E9%9B%B2%E6%9E%97%E7%B8%A3,%E5%98%89%E7%BE%A9%E7%B8%A3,%E5%98%89%E7%BE%A9%E5%B8%82,%E5%B1%8F%E6%9D%B1%E7%B8%A3";
            mView.replace36hrFragment(apiUrl);
        }
    }
}
