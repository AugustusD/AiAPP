package com.duanshl.aiapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.duanshl.aiapp.Utils.OkHttpUtil;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String email;
    private String password;

    private Button btn;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param email Parameter 1.
     * @param password Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String email, String password) {
        LoginFragment fragment = new LoginFragment();
        //Bundle用于activity之间传递数据
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, email);
        args.putString(ARG_PARAM2, password);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater)getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View rootView = inflater.inflate(R.layout.fragment_login,null);

        final TextView et_email = (TextView) getActivity().findViewById(R.id.log_email);
        final TextView et_password = (TextView) getActivity().findViewById(R.id.log_password);


        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//                System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ");

                String loginUrl="http://10.0.2.2:8080/user/login";
                String loginAccount = et_email.getText().toString();
                String loginPassword = et_password.getText().toString();
                try {
                    loginWithOkHttp(loginUrl,loginAccount,loginPassword);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_login,container,false);
        btn = (Button) rootView.findViewById(R.id.btn_login);

        return rootView;
    }

    //实现登录
    public void loginWithOkHttp(String address,String account,String password)throws JSONException{
        OkHttpUtil.loginWithOkHttp(address,account,password, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //在这里对异常情况进行处理
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //得到服务器返回的具体内容
                final String responseData = response.body().string();

                final TextView et_password = (TextView) getActivity().findViewById(R.id.log_password);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (responseData.equals("true")){
                            Toast.makeText(getActivity(),"登录成功",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        }else{
                            Toast.makeText(getActivity(),"登录失败,请重试",Toast.LENGTH_SHORT).show();
                            //密码栏清空，重新输入
                            et_password.setText("");
                        }
                    }
                });
            }
        });
    }




}