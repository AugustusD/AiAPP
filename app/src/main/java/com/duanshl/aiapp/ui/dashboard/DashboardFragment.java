package com.duanshl.aiapp.ui.dashboard;

import android.os.Bundle;
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

import com.duanshl.aiapp.R;
import com.duanshl.aiapp.Utils.OkHttpUtil;
import com.duanshl.aiapp.data.model.ArticleBean;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DashboardFragment extends Fragment {

    private View view;
    public RecyclerView recyclerView;
    private ArticleRecycleAdapter articleRecycleAdapter;
    private ArrayList<ArticleBean> articleBeanArrayList = new ArrayList<ArticleBean>();
    private DashboardViewModel dashboardViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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


        initRecyclerView();
        initData();


        return view;
    }

    /**
     * TODO 模拟数据
     */
    private void initData() {
        for (int i=0;i<10;i++){
            ArticleBean articleBean=new ArticleBean();
            articleBean.setTitle("setTitle"+i);
            articleBean.setAuthor("setAuthor"+i);
            articleBean.setAddress("setAddress"+i);
            articleBean.setDate(new Date());
            articleBeanArrayList.add(articleBean);
        }




    }

    public void download(){
        OkHttpUtil.downloadArticles(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseData = response.body().toString();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });


            }
        });
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
                Toast.makeText(getActivity(),"我是item:"+data.getTitle(),Toast.LENGTH_SHORT).show();
            }
            @Override
            public  void OnItemLongClick(View view, ArticleBean data) {
                Toast.makeText(getActivity(), "long click:"+data.getAuthor(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}