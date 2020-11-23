package com.duanshl.aiapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.baidu.mapapi.map.MapView;
import com.duanshl.aiapp.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private MapView mMapView = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
//                requireActivity().setContentView(R.layout.fragment_home);
//                //获取地图控件引用
//                mMapView = (MapView) getActivity().findViewById(R.id.bmapView);
            }
        });
        return root;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
//        mMapView.onResume();
//    }
//    @Override
//    public void onPause() {
//        super.onPause();
//        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
//        mMapView.onPause();
//    }
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
//        mMapView.onDestroy();
//    }
}