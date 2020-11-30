package com.duanshl.aiapp.ui.LR;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.leo.copytoutiao.DataApplication;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

public class LoginFragment extends Fragment {


    static Boolean loginSuccess = false;

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
        args.putString("email", email);
        args.putString("password", password);
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

                String loginUrl="http://47.101.135.103:8080/user/login";
                String loginAccount = et_email.getText().toString();
                String loginPassword = et_password.getText().toString();
                try {
                    System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ");


                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("art", MODE_PRIVATE);
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString("email", loginAccount);
                    edit.commit();

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
                System.out.println("出异常啦出异常啦出异常啦出异常啦");
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
                        System.out.println("登录马上成功！");
                        //根据返回的数据组成,截取进行判断
                        String ans = responseData.substring(0,4);
                        String username = responseData.substring(5);

                        DataApplication dataApplication = null;
                        //把用户名放置在DataApplication中
//                        dataApplication.setAuthor(username);

                        if (ans.equals("true")){
                            Toast.makeText(getActivity(),"登录成功",Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        }else{
                            Toast.makeText(getActivity(),"登录失败,请检查账号密码是否正确",Toast.LENGTH_SHORT).show();
                            //密码栏清空，重新输入
                            et_password.setText("");
                        }
                    }
                });
            }
        });

    }

}