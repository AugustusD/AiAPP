package com.duanshl.aiapp.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkHttpUtil {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    //登录
    public static void loginWithOkHttp(String address, String email, String password, okhttp3.Callback callback) throws JSONException {
//        JsonObject json = new JsonObject();
//        json.addProperty("loginEmail",email);
//        json.addProperty("loginPassword",password);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("loginEmail",email);
        jsonObject.put("loginPassword",password);
        String jj = jsonObject.toString();

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(jj,JSON);
        Request request = new Request.Builder()
                .url(address)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }
    //注册
    public static void registerWithOkHttp(String address, String email, String password, String userName, okhttp3.Callback callback) throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("registerEmail",email);
        jsonObject.put("registerPassword",password);
        jsonObject.put("registerUserName",userName);
        String jj = jsonObject.toString();

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(jj,JSON);
        Request request = new Request.Builder()
                .url(address)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }
}