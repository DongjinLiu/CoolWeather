package com.example.jin.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jin on 2017/7/22.
 */

public class Now {
    @SerializedName("tmp")
    public String temperature;

    @SerializedName("cond")
    public More more;

    public class More{
        @SerializedName("txt")
        public String info;
    }
}
