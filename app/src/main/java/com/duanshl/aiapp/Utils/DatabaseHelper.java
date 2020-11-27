package com.duanshl.aiapp.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    //数据库版本号
    private static Integer version = 1;

    private static final String DB_NAME="Story.db";
    private static final String TABLE_NAME="story";
    private static final String CREATE_TABLE="create table story(" +
            "_id integer primary key autoincrement,arti_author text,arti_title text,arti_content text, arti_img text, arti_create datetime)";
    private SQLiteDatabase db;

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory cursorFactory, int version)
    {
        super(context, DB_NAME, cursorFactory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO 创建数据库后，对数据库的操作
        this.db=db;
        db.execSQL(CREATE_TABLE);
    }

    public void insert(ContentValues values){
        SQLiteDatabase db=getWritableDatabase();
        db.insert(TABLE_NAME,null,values);
        db.close();
    }


    public void del(int id){
        if(null==db)
            db=getWritableDatabase();
        db.delete(TABLE_NAME,"_id=?",new String[]{String.valueOf(id)});
    }
    public Cursor query(){
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME,null,null,null,null,null,null);
        return cursor;
    }
    public void close()
    {
        if(db!=null)
            db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO 更改数据库版本的操作
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        // TODO 每次成功打开数据库后首先被执行
    }


}