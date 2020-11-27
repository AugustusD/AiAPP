package com.duanshl.aiapp.ui.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.duanshl.aiapp.R;
import com.leo.copytoutiao.activity.PublishActivity;

public class MeFragment extends Fragment {

    private MeViewModel meViewModel;
    Button button;
    Button button1;

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        meViewModel =
                new ViewModelProvider(this).get(MeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_me, container, false);
        final TextView textView = root.findViewById(R.id.text_me);
        meViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        button = (Button) root.findViewById(R.id.btn_activity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),AboutInfoActivity.class);
                startActivity(intent);
            }
        });


        button1 = (Button) root.findViewById(R.id.myMap_Acti);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //用的是RichEditTextLib这个module里的
                Intent intent = new Intent(getActivity(), PublishActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }
}