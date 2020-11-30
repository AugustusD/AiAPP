package com.duanshl.aiapp.data.model;

import java.util.Date;

public class ArticleBean {

    public String uuid;
    public String title;
    public String author;
    public Date date;
    public String content;
    public String location;
    public String address;

    public ArticleBean() {

    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArticleBean(String uuid, String title, String author, Date date, String content, String location, String address) {
        this.uuid = uuid;
        this.author = author;
        this.title = title;
        this.content = content;
        this.location = location;
        this.address = address;
        this.date = date;
    }
}
