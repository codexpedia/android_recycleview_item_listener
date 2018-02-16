package com.example.recycleview_click_listener.model;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.example.recycleview_click_listener.R;

import java.util.ArrayList;
import java.util.List;

public class CityManager extends Application {
    public final static String NAME_PREFIX = "name_";
    public final static String DESC_PREFIX = "desc_";

    private Context context;
    private static String[] citykeyArray = {""};
    private static CityManager mInstance;
    private List<City> cities;

    public CityManager(Context c) {
        this.context = c;
        this.citykeyArray = c.getResources().getStringArray(R.array.city_keys);
    }

    public static CityManager getInstance(Context c) {
        if (mInstance == null) {mInstance = new CityManager(c);}
        return mInstance;
    }

    public List<City> getCites() {
        if (cities == null) {
            cities = new ArrayList<City>();
            for (String cityKey : citykeyArray) {
                City city = new City();

                Resources res = context.getResources();
                String packageName = context.getPackageName();
                int id = res.getIdentifier(NAME_PREFIX + cityKey, "string", packageName);
                city.name = context.getString(id);
                city.description = context.getString(context.getResources().getIdentifier(DESC_PREFIX + cityKey, "string", context.getPackageName()));
                city.imageName = cityKey.toLowerCase();
                cities.add(city);
            }
        }
        return  cities;
    }

}
