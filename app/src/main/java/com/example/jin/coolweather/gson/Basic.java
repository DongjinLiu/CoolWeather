package com.example.jin.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jin on 2017/7/22.
 */

public class Basic {
    @SerializedName("city")
    public String cityName;//city不适合做变量名，用上面一条语句关联JSON字段

    @SerializedName("id")
    public String weatherId;

    public Update update;

    public class Update{
        @SerializedName("loc")
        public String updateTime;
    }
}
