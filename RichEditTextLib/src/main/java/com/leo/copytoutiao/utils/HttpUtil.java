package com.leo.copytoutiao.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");



    public static void getLocInfo(String location, okhttp3.Callback callback) throws JSONException {

        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("ak","8jpNRIQH25ofN9W3t0A0kyGkGT7UaFys")
                .add("output","json")
                .add("coordtype","wgs84ll")
                .add("location",location)
                .add("mcode","E6:08:F1:A0:E0:62:8C:A5:32:A9:8F:A7:CA:1C:D0:41:F0:92:C9:76;com.duanshl.aiapp")
                .build();
        Request request = new Request.Builder()
                .url("http://api.map.baidu.com/reverse_geocoding/v3/")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static String getImgNetUrl(String imgBase64) throws IOException, JSONException {
        String token = "7333a25564d141999486f90123852bdb";
        String category = "aiapp";
        OkHttpClient client = new OkHttpClient();
            RequestBody formBody = new FormBody.Builder()
                    .add("token",token)
                    .add("v","1")
                    .add("categories",category)
                    .add("b64_data",imgBase64)
                    .build();
            Request request = new Request.Builder()
                    .url("https://api.superbed.cn/upload")
                    .post(formBody)
                    .build();
        Call call = client.newCall(request);
        Response response = call.execute();

        System.out.println("response ====== " + response);

        JSONObject jsonObject = new JSONObject(response.body().string());

        String imgNetUrl = (String) jsonObject.get("url");
        return imgNetUrl;
    }

}