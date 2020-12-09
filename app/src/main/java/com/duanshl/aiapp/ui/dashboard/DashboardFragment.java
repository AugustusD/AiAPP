package com.duanshl.aiapp.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.duanshl.aiapp.R;
import com.duanshl.aiapp.data.model.ArticleBean;
import com.duanshl.aiapp.ui.article.ArticlePreciseActivity;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DashboardFragment extends Fragment {


    private View view;
    public RecyclerView recyclerView;
    private ArticleRecycleAdapter articleRecycleAdapter;
    private ArrayList<ArticleBean> articleBeanArrayList = new ArrayList<ArticleBean>();
    private DashboardViewModel dashboardViewModel;
//    private static String responseData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        view = inflater.inflate(R.layout.fragment_dashboard, container, false);
//        final TextView textView = view.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });

        try {
            download();
            initRecyclerView();
            initData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;
    }

    /**
     * TODO 模拟数据
     */
    private void initData() throws IOException{
        String responseData = download();
//        System.out.println("data ================ " + responseData);
        JSONObject jsonObject = JSONObject.parseObject(responseData);
        System.out.println("count =========== "+jsonObject.getString("count"));
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        for (int i = 0; i < jsonArray.size(); i++) {
            ArticleBean articleBean = new ArticleBean();
            JSONObject object = new JSONObject(jsonArray.getJSONObject(i));
            articleBean.setUuid(object.getString("artiUuid"));
            articleBean.setTitle(object.getString("artiTitle"));
            articleBean.setAuthor("作者: " + object.getString("artiAuthor"));
            articleBean.setDate("日期: " + object.getString("artiDate"));
            articleBean.setContent(object.getString("artiContent"));
            articleBean.setLocation(object.getString("artiLocation"));
            articleBean.setAddress("故事地点: " + object.getString("artiAddress"));
            articleBean.setImgUrl(object.getString("artiImg"));
            articleBeanArrayList.add(articleBean);
        }

    }

    public String download() throws IOException {
//        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient = new OkHttpClient();
        String getArticles_url = "http://47.101.135.103:8080/article/articles";
        Request request = new Request.Builder()
                .url(getArticles_url)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        String res = response.body().string();
        System.out.println("response body ============= " + res);
        return res;
    }


    /**
     * TODO 对recycleview进行配置
     */

    private void initRecyclerView() {
        //获取RecyclerView
        recyclerView=(RecyclerView)view.findViewById(R.id.dashboard_recyclerView);
        //创建adapter
        articleRecycleAdapter = new ArticleRecycleAdapter(getActivity(), articleBeanArrayList);
        //给RecyclerView设置adapter
        recyclerView.setAdapter(articleRecycleAdapter);
        //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
        //参数是：上下文、列表方向（横向还是纵向）、是否倒叙
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        //设置item的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        //RecyclerView中没有item的监听事件，需要自己在适配器中写一个监听事件的接口。参数根据自定义
        articleRecycleAdapter.setOnItemClickListener(new ArticleRecycleAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, ArticleBean data) {
                //进入article之中
                //此处进行监听事件的业务处理
//                Toast.makeText(getActivity(),"我是item:"+data.getTitle(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), ArticlePreciseActivity.class);
                intent.putExtra("content", data.getContent());
                startActivity(intent);

            }
            @Override
            public  void OnItemLongClick(View view, ArticleBean data) {
                Toast.makeText(getActivity(), "长按的功能还没有开发出来~", Toast.LENGTH_SHORT).show();
            }
        });
    }


}