package com.weather.weathertools.tools;

import android.content.Context;
import android.util.Log;
import android.widget.ProgressBar;

import com.weather.weathertools.R;

import java.util.ArrayList;

public class TitleProvider {
    private static TitleProvider titleProvider;

    private Context context;

    private TitleProvider (Context context){
        this.context = context;
    }
    public static TitleProvider getInstance(Context context){
        if (titleProvider == null) {
            titleProvider = new TitleProvider(context);
        }
        return titleProvider;
    }
    public ArrayList<String> getBroadCastArray(){
        ArrayList<String> braodCastArray = new ArrayList<>();
        braodCastArray.add(context.getString(R.string.broad_title_1));
        braodCastArray.add(context.getString(R.string.broad_title_2));
        braodCastArray.add(context.getString(R.string.broad_title_3));
        braodCastArray.add(context.getString(R.string.broad_title_4));
        braodCastArray.add(context.getString(R.string.broad_title_5));
        braodCastArray.add(context.getString(R.string.broad_title_6));
        braodCastArray.add(context.getString(R.string.broad_title_7));
        braodCastArray.add(context.getString(R.string.broad_title_8));
        braodCastArray.add(context.getString(R.string.broad_title_9));
        braodCastArray.add(context.getString(R.string.broad_title_10));
        braodCastArray.add(context.getString(R.string.broad_title_11));
        braodCastArray.add(context.getString(R.string.broad_title_12));
        braodCastArray.add(context.getString(R.string.broad_title_13));
        braodCastArray.add(context.getString(R.string.broad_title_15));
        braodCastArray.add(context.getString(R.string.broad_title_16));
        braodCastArray.add(context.getString(R.string.broad_title_17));
        braodCastArray.add(context.getString(R.string.broad_title_18));
        braodCastArray.add(context.getString(R.string.broad_title_19));
        braodCastArray.add(context.getString(R.string.broad_title_20));
        braodCastArray.add(context.getString(R.string.broad_title_21));
        braodCastArray.add(context.getString(R.string.broad_title_22));
        braodCastArray.add(context.getString(R.string.broad_title_23));
        braodCastArray.add(context.getString(R.string.broad_title_24));
        braodCastArray.add(context.getString(R.string.broad_title_25));
        braodCastArray.add(context.getString(R.string.broad_title_26));
        braodCastArray.add(context.getString(R.string.broad_title_27));
        braodCastArray.add(context.getString(R.string.broad_title_28));
        braodCastArray.add(context.getString(R.string.broad_title_29));
        braodCastArray.add(context.getString(R.string.broad_title_30));
        braodCastArray.add(context.getString(R.string.broad_title_31));
        braodCastArray.add(context.getString(R.string.broad_title_32));
        braodCastArray.add(context.getString(R.string.broad_title_33));
        braodCastArray.add(context.getString(R.string.broad_title_34));
        braodCastArray.add(context.getString(R.string.broad_title_35));
        braodCastArray.add(context.getString(R.string.broad_title_36));
        braodCastArray.add(context.getString(R.string.broad_title_37));
        braodCastArray.add(context.getString(R.string.broad_title_38));
        braodCastArray.add(context.getString(R.string.broad_title_39));
        braodCastArray.add(context.getString(R.string.broad_title_40));
        braodCastArray.add(context.getString(R.string.broad_title_41));
        braodCastArray.add(context.getString(R.string.broad_title_42));
        braodCastArray.add(context.getString(R.string.broad_title_43));
        braodCastArray.add(context.getString(R.string.broad_title_44));
        braodCastArray.add(context.getString(R.string.broad_title_45));
        braodCastArray.add(context.getString(R.string.broad_title_46));
        braodCastArray.add(context.getString(R.string.broad_title_47));
        braodCastArray.add(context.getString(R.string.broad_title_48));
        braodCastArray.add(context.getString(R.string.broad_title_49));
        return braodCastArray;
    }

    public ArrayList<String> get3DaysApiUrlArray(){
        ArrayList<String> apiUrlArray = new ArrayList<>();
        for (int i = 1 ; i <= 89 ; i += 4){
            String number;
            if (i < 10){
                number = "0"+i;
            }else {
                number = i+"";
            }
            apiUrlArray.add("https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-D0047-0"+number+"?Authorization=CWB-CF93991C-7A79-4387-8A8B-D5F583B50AEC&format=JSON&elementName=Wx,PoP6h,AT,CI");
        }
        return apiUrlArray;
    }

    public ArrayList<String> getOneWeekApiUrlArray(){
        ArrayList<String> apiUrlArray = new ArrayList<>();
        for (int i = 3 ; i <= 87 ; i +=4){
            String number;
            if (i < 10){
                number = "0"+i;
            }else {
                number = i+"";
            }
            apiUrlArray.add("https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-D0047-0"+number+"?Authorization=CWB-CF93991C-7A79-4387-8A8B-D5F583B50AEC&format=JSON&elementName=MaxCI,MinT,MaxT,PoP12h,Wx");
        }
        return apiUrlArray;
    }
}
