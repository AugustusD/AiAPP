package com.leo.copytoutiao;

import android.app.Application;

//This class is used to share the information between different activities
public class DataApplication extends Application {


    //geographic position(longitude and latitude)
    String location = "";

    //specific location information
    String address = "";

    //date
    String date = "";

    //author
    String author = "";

    //RichEditText's title
    String title = "";

    //RichEditText information
    String data = "";

    String imgUrl = "";

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getData(){
        return data;
    }

    public void setData(String data){
        this.data = data;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
