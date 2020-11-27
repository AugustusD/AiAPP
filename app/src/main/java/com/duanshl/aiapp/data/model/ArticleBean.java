package com.duanshl.aiapp.data.model;

import java.util.Date;

public class ArticleBean {

    public String id;

    public String author;

    public String title;

    public String content;

    //把图片传到服务器,本地只存储图片的网络地址
    public String img;

    public Date date;

    public boolean isCheck;

    public ArticleBean(String id, String author, String title, String content, String img, Date date) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.img = img;
        this.date = date;
    }
}
