package com.leo.copytoutiao.utils;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class ArticleUtils {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    /**
     * 发布文章到服务器
     * @param title
     * @param author
     * @param date
     * @param content
     * @param location
     * @param address
     * @param callback
     * @throws JSONException
     */
    public static void publish(String title, String author, String date, String content, String location, String address, String imgUrl, okhttp3.Callback callback) throws JSONException{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("arti_title",title);
        jsonObject.put("arti_author",author);
        jsonObject.put("arti_date",date);
        jsonObject.put("arti_content",content);
        jsonObject.put("arti_location",location);
        jsonObject.put("arti_address",address);
        jsonObject.put("arti_img",imgUrl);
        String jj = jsonObject.toString();
        System.out.println(jj);

        String publish_url = "http://47.101.135.103:8080/article/publish";

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(jj,JSON);
        Request request = new Request.Builder()
                .url(publish_url)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);

    }
//    public static void downloadArticles(){
//
//    }
}
