package com.duanshl.aiapp.ui.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.duanshl.aiapp.R;
import com.duanshl.aiapp.ui.item.ItemView;

public class MeFragment extends Fragment {

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

    private MeViewModel meViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(getActivity(),ProfileActivity.class);
        startActivity(intent);

    }

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        Timer timer = new Timer();// 实例化Timer类
//        timer.schedule(new TimerTask() {
//            public void run() {
//                System.out.println("退出");
//                this.cancel();
//            }
//        }, 100000);// 这里百毫秒
        meViewModel =
                new ViewModelProvider(this).get(MeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_me, container, false);
//        final TextView textView = root.findViewById(R.id.text_me);
        meViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                ImageView imageView = getActivity().findViewById(R.id.imageView);
                imageView.setImageResource(R.drawable.egg);
            }
        });



        return root;
    }
}