package com.example.jin.coolweather.util;

import android.text.TextUtils;
import android.util.Log;

import com.example.jin.coolweather.db.City;
import com.example.jin.coolweather.db.County;
import com.example.jin.coolweather.db.Province;
import com.example.jin.coolweather.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jin on 2017/7/22.
 *
 * 用来解析各类返回的数据String
 */

public class Utility {

    private static final String TAG="Utility";

    /**
     * 解析和处理服务器返回的省级数据
     * 存入数据库
     * @param response 省数据
     * @return 处理结果
     */
    public static boolean handleProvinceResponse(String response){
        //Log.d(TAG, "handleProvinceResponse: OK");
        if (!TextUtils.isEmpty(response)){
            try{
                //Log.d(TAG, "handleProvinceResponse: OK");
                JSONArray allProvinces=new JSONArray(response);
                Log.d(TAG, "handleProvinceResponse: len"+allProvinces.length());
                for (int i=0;i<allProvinces.length();i++){
                    JSONObject provinceObject=allProvinces.getJSONObject(i);
                    Province province=new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //Log.d(TAG, "handleProvinceResponse: NO");
        return false;
    }

    /**
     * 解析和处理服务器返回的市级数据
     * 存入数据库
     * @param response 服务器返回的市数据
     * @param provinceId 选中的省
     * @return 处理结果
     */
    public static boolean handleCityResponse(String response,int provinceId){
        if (!TextUtils.isEmpty(response)){
            try{
                JSONArray allCitys=new JSONArray(response);
                for (int i=0;i<allCitys.length();i++){
                    JSONObject cityObject=allCitys.getJSONObject(i);
                    City city=new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的县级数据
     * 存入数据库
     * @param response 服务器返回的JSON数据
     * @param cityId 选中的市
     * @return 解析结果
     */
    public static boolean handleCountyResponse(String response,int cityId){
        if (!TextUtils.isEmpty(response)){
            try{
                JSONArray allCountys=new JSONArray(response);
                for (int i=0;i<allCountys.length();i++){
                    JSONObject countyObject=allCountys.getJSONObject(i);
                    County county=new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 将返回的JSON数据解析成Weather实体类
     * @param response 服务器返回的JSON数据
     * @return 解析出来的Weather类
     */
    public static Weather handleWeatherResponse(String response){
        try {
            JSONObject jsonObject=new JSONObject(response);
            JSONArray jsonArray=jsonObject.getJSONArray("HeWeather");
            String weatherContent=jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent,Weather.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
