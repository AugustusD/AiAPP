package com.duanshl.aiapp.Utils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginService {
    public static final String BASE_URL = "http://47.101.135.103:8080/Server/login/";

    OkHttpClient okHttpClient = new OkHttpClient();

    public String login(String username, String password)
            throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL + "/users")
                .post(formBody)
                .build();

        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.body().string());
            return "OK";
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}