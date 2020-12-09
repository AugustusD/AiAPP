package com.duanshl.aiapp.ui.me;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.duanshl.aiapp.R;
import com.duanshl.aiapp.ui.item.ItemView;
import com.duanshl.aiapp.ui.recognition.CommonRecg;
import com.leo.copytoutiao.activity.PublishActivity;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleWithBorderTransformation;

public class ProfileActivity extends AppCompatActivity {

    private ImageView mHBack;
    private ImageView mHHead;
    private ImageView mUserLine;
    private TextView mUserName;
    private TextView mUserVal;
    private TextView mUserMotto;

    private ItemView mWrite;
    private ItemView mPhoto;
    private ItemView mImprove;
    private ItemView mPass;
    private ItemView mPhone;
    private ItemView mVersion;
    private ItemView mFeed;
    private ItemView mSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initView();
        setData();
        click();
    }


    private void click(){
        //整个item的点击事件
        mWrite.setItemClickListener(new ItemView.itemClickListener() {
            @Override
            public void itemClick(String text) {
                Intent intent = new Intent(getApplicationContext(), PublishActivity.class);
                Toast.makeText(getApplicationContext(), "开始写你的故事吧！", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        mPhoto.setItemClickListener(new ItemView.itemClickListener() {
            @Override
            public void itemClick(String text) {
                Intent intent = new Intent(getApplicationContext(), CommonRecg.class);
                Toast.makeText(getApplicationContext(), "开始写你的故事吧！", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        mImprove.setItemClickListener(new ItemView.itemClickListener() {
            @Override
            public void itemClick(String text) {
                Toast.makeText(getApplicationContext(), "点击啦", Toast.LENGTH_SHORT).show();
            }
        });
        mPass.setItemClickListener(new ItemView.itemClickListener() {
            @Override
            public void itemClick(String text) {
                Toast.makeText(getApplicationContext(), "点击啦", Toast.LENGTH_SHORT).show();
            }
        });
        mPhone.setItemClickListener(new ItemView.itemClickListener() {
            @Override
            public void itemClick(String text) {
                Toast.makeText(getApplicationContext(), "点击啦", Toast.LENGTH_SHORT).show();
            }
        });
        mVersion.setItemClickListener(new ItemView.itemClickListener() {
            @Override
            public void itemClick(String text) {
                Intent intent = new Intent(getApplicationContext(), AboutInfoActivity.class);
                startActivity(intent);
            }
        });
        mFeed.setItemClickListener(new ItemView.itemClickListener() {
            @Override
            public void itemClick(String text) {
                Toast.makeText(getApplicationContext(), "点击啦", Toast.LENGTH_SHORT).show();
            }
        });
        mSetting.setItemClickListener(new ItemView.itemClickListener() {
            @Override
            public void itemClick(String text) {
                Toast.makeText(getApplicationContext(), "点击啦", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setData() {
        //设置背景磨砂效果
        Glide.with(this)
                .load(R.drawable.sky)
                .transform(new BlurTransformation(50))
                .into(mHBack);
        //设置圆形图像
        Glide.with(this)
                .load(R.drawable.bighead)
                .transform(new CropCircleWithBorderTransformation(0,0))
                .into(mHHead);
    }

    private void initView() {
        //顶部头像控件
        mHBack = (ImageView) findViewById(R.id.h_back);
        mHHead = (ImageView) findViewById(R.id.h_head);
        mUserLine = (ImageView) findViewById(R.id.user_line);
        mUserName = (TextView) findViewById(R.id.user_name);
        mUserVal = (TextView) findViewById(R.id.user_val);
        mUserMotto = (TextView) findViewById(R.id.user_motto);
        //下面item控件
        mWrite = (ItemView) findViewById(R.id.write);
        mPhoto = (ItemView) findViewById(R.id.photo);
        mImprove = (ItemView) findViewById(R.id.improve);
        mPass = (ItemView) findViewById(R.id.pass);
        mPhone = (ItemView) findViewById(R.id.phone);
        mVersion = (ItemView) findViewById(R.id.version);
        mFeed = (ItemView) findViewById(R.id.feed);
        mSetting = (ItemView) findViewById(R.id.setting);

    }
}