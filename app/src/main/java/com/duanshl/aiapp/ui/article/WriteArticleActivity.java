package com.duanshl.aiapp.ui.article;

import android.content.ContentValues;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.duanshl.aiapp.R;

public class WriteArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_article);
    }
    public void insert(String name, String address, String type, String notes) {
        ContentValues cv=new ContentValues();

        cv.put("name", name);
        cv.put("address", address);
        cv.put("type", type);
        cv.put("notes", notes);

//        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext(), "Story.db", cv, 1);

//            DatabaseHelper().insert("restaurants", "name", cv);
    }
}