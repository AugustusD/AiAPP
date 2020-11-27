package com.duanshl.aiapp.ui.LR;

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

import com.duanshl.aiapp.MainActivity;
import com.duanshl.aiapp.R;
import com.duanshl.aiapp.Utils.OkHttpUtil;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button btn;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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

        final TextView et_name = getActivity().findViewById(R.id.et_name);
        final TextView et_email = getActivity().findViewById(R.id.et_email);
        final TextView et_password = getActivity().findViewById(R.id.et_password);
        final TextView et_repassword = getActivity().findViewById(R.id.et_repassword);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//                System.out.println("注册注册注册注册注册注册注册注册注册注册");

                String registerUrl="http://47.101.135.103:8080/user/register";
                String registerEmail = et_email.getText().toString();
                String registerPassword = et_password.getText().toString();
                String registerUserName = et_name.getText().toString();
                String registerRePassword = et_repassword.getText().toString();
                if (!registerPassword.equals(registerRePassword)){
                    Toast.makeText(getActivity(),"两次输入密码不一致，请重新输入",Toast.LENGTH_SHORT).show();
                    //清空密码和重复密码
                    et_password.setText("");
                    et_repassword.setText("");
                    //焦点放在密码上
                    et_password.requestFocus();
                }else {
                    try {
                        registerWithOkHttp(registerUrl, registerEmail, registerPassword, registerUserName);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_register,container,false);
        btn = (Button) rootView.findViewById(R.id.btn_register);

        return rootView;
    }

    //实现注册
    public void registerWithOkHttp(String address,String email,String password,String userName) throws JSONException {
        OkHttpUtil.registerWithOkHttp(address, email, password,userName, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //在这里对异常情况进行处理
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseData = response.body().string();
                final TextView et_email = (TextView) getActivity().findViewById(R.id.et_email);
                final TextView et_name = (TextView) getActivity().findViewById(R.id.et_name);
                final TextView et_password = (TextView) getActivity().findViewById(R.id.et_password);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (responseData.equals("true")){
                            Toast.makeText(getActivity(),"注册成功",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        }else{
                            Toast.makeText(getActivity(),"注册失败,邮箱已被注册",Toast.LENGTH_SHORT).show();
                            //密码栏清空，重新输入
                            et_password.setText("");
                            et_email.setText("");
                        }
                    }
                });
            }
        });
    }


}