package com.duanshl.aiapp.ui.home;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.duanshl.aiapp.R;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private LocationClient mLocClient;
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private BitmapDescriptor bitmap;
    private String address= "";
    // 是否首次定位
    boolean isFirstLoc = true;

//    static MapFragment newInstance() {
//        MapFragment f = new MapFragment();
//        return f;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);

            }
        });
        // 地图初始化
        mMapView = (MapView) root.findViewById(R.id.bmapView);
        //设置是否显示比例尺控件
        mMapView.showScaleControl(false);
        // 删除百度地图LoGo
        mMapView.removeViewAt(1);
        mBaiduMap = mMapView.getMap();
//        final MyLocationConfiguration.LocationMode mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        // 定位初始化
        initLocation();
        // 点击地图获取位置
        startMark();
        return root;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    /**
     * 定位初始化
     */
    public void initLocation(){
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(getActivity());
        HomeFragment.MyLocationListenner myListener = new HomeFragment.MyLocationListenner();
        mLocClient.registerLocationListener(myListener);

        LocationClientOption option = new LocationClientOption();
        // 打开gps
        option.setOpenGps(true);
        // 设置坐标类型
        option.setCoorType("bd09ll");
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();
    }

    /**
     * 点击地图获取经纬度信息
     */
    public void startMark(){
        // 设置marker图标
        bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_redmarker);
        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {

            @Override
            public void onMapPoiClick(MapPoi arg0) {
                // TODO Auto-generated method stub
            }

            //此方法就是点击地图监听
            @Override
            public void onMapClick(LatLng latLng) {
                //获取经纬度
                double latitude = latLng.latitude;
                double longitude = latLng.longitude;
                System.out.println("latitude=" + latitude + ",longitude=" + longitude);
                //先清除图层
                mBaiduMap.clear();
                // 定义Maker坐标点
                LatLng point = new LatLng(latitude, longitude);
                // 构建MarkerOption，用于在地图上添加Marker
                MarkerOptions options = new MarkerOptions().position(point)
                        .icon(bitmap);
                // 在地图上添加Marker，并显示
                mBaiduMap.addOverlay(options);
                //实例化一个地理编码查询对象
                GeoCoder geoCoder = GeoCoder.newInstance();
//                //设置反地理编码位置坐标
//                ReverseGeoCodeOption op = new ReverseGeoCodeOption()
//                        .location(latLng);
//                op.location(latLng);
                //发起反地理编码请求(经纬度->地址信息)
//                geoCoder.reverseGeoCode(op);
                geoCoder.reverseGeoCode(new ReverseGeoCodeOption()
                        .location(latLng)
                        // 设置是否返回新数据 默认值0不返回，1返回
                        .newVersion(1)
                        // POI召回半径，允许设置区间为0-1000米，超过1000米按1000米召回。默认值为1000
                        .radius(500));

                geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
                    @Override
                    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                        if (reverseGeoCodeResult == null || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                            //没有找到检索结果
                            System.out.println("没有找到检索结果");
                            return;
                        } else {
                            //详细地址
                            String address = reverseGeoCodeResult.getAddress();
                            //行政区号
                            int adCode = reverseGeoCodeResult. getCityCode();
                        }
                        System.out.println("详细地址==========="+address);
                    }
                    @Override
                    public void onGetGeoCodeResult(GeoCodeResult arg0) {
                    }
                });

                geoCoder.destroy();
            }
        });
    }

    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // MapView 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())// 设置定位数据的精度信息，单位：米
                    .direction(location.getDirection()) // 此处设置开发者获取到的方向信息，顺时针0-360
                    .latitude(location.getLatitude())
                    .longitude(location.getLongitude())
                    .build();
            // 设置定位数据, 只有先允许定位图层后设置数据才会生效
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(latLng).zoom(20.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // 在activity执行onResume时必须调用mMapView. onResume ()
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        // 在activity执行onPause时必须调用mMapView. onPause ()
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 退出时销毁定位
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        // 在activity执行onDestroy时必须调用mMapView.onDestroy()
        mMapView.onDestroy();
    }
}

