package com.example.jin.coolweather.gson;

/**
 * Created by jin on 2017/7/22.
 */

public class AQI {
    public AQICity city;

    public class AQICity{
        public String aqi;
        public String pm25;
    }
}
