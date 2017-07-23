package com.example.jin.coolweather.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by jin on 2017/7/22.
 *
 * 联网时用
 */




public class HttpUtil {
    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
