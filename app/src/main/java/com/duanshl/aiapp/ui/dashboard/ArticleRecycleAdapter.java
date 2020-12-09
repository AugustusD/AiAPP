package com.duanshl.aiapp.ui.dashboard;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.duanshl.aiapp.R;
import com.duanshl.aiapp.data.model.ArticleBean;

import java.util.ArrayList;

public class ArticleRecycleAdapter extends RecyclerView.Adapter<ArticleRecycleAdapter.myViewHodler> {
    private Context context;
    private ArrayList<ArticleBean> articleBeanArrayList;


    //创建构造函数
    public ArticleRecycleAdapter(Context context, ArrayList<ArticleBean> articleBeanArrayList) {
        //将传递过来的数据，赋值给本地变量
        this.context = context;//上下文
        this.articleBeanArrayList = articleBeanArrayList;//实体类数据ArrayList
    }

    //创建viewhodler，相当于listview中getview中的创建view和viewhodler

    @Override
    public myViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建自定义布局
        View itemView = View.inflate(context, R.layout.article_item_layout, null);
        return new myViewHodler(itemView);
    }

    //绑定数据，数据与view绑定
    @Override
    public void onBindViewHolder(myViewHodler holder, int position) {
        //根据点击位置绑定数据
        ArticleBean data = articleBeanArrayList.get(position);
        //        holder.mItemGoodsImg;
        holder.articleTitle.setText(data.title);//获取实体类中的name字段并设置
        holder.articleAuthor.setText(data.author);//获取实体类中的price字段并设置
        holder.articleDate.setText(data.date.toString());//获取实体类中的price字段并设置
        holder.articleAddress.setText(data.address);//获取实体类中的price字段并设置
//        holder.articleImage.setImageURI(Uri.parse(data.imgUrl));
        Glide.with(context).load(data.imgUrl).into(holder.articleImage);
    }

    //得到总条数
    @Override
    public int getItemCount() {
        return articleBeanArrayList.size();
    }


    //自定义viewhodler
    class myViewHodler extends RecyclerView.ViewHolder {
        private ImageView articleImage;
        private TextView articleTitle;
        private TextView articleAuthor;
        private TextView articleDate;
        private TextView articleAddress;

        public myViewHodler(final View itemView) {
            super(itemView);
            articleImage = (ImageView) itemView.findViewById(R.id.article_image);
            articleTitle = (TextView) itemView.findViewById(R.id.article_title);
            articleAuthor = (TextView) itemView.findViewById(R.id.article_author);
            articleDate = (TextView) itemView.findViewById(R.id.article_date);
            articleAddress = (TextView) itemView.findViewById(R.id.article_address);
            //点击事件放在adapter中使用，也可以写个接口在activity中调用
            //方法一：在adapter中设置点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //可以选择直接在本位置直接写业务处理
                    //Toast.makeText(context,"点击了xxx",Toast.LENGTH_SHORT).show();
                    //此处回传点击监听事件
                    if (onItemClickListener != null) {
                        onItemClickListener.OnItemClick(v, articleBeanArrayList.get(getLayoutPosition()));

                    }
                }
            });


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.OnItemLongClick(v, articleBeanArrayList.get(getLayoutPosition()));
                    }
                    return true;
                }
            });
        }
    }

    /**
     * 设置item的监听事件的接口
     */
    public interface OnItemClickListener {
        /**
         * 接口中的点击每一项的实现方法，参数自己定义
         *
         * @param view 点击的item的视图
         * @param data 点击的item的数据
         */
        public void OnItemClick(View view, ArticleBean data);
        public void OnItemLongClick(View view, ArticleBean data);
    }

    //需要外部访问，所以需要设置set方法，方便调用
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


}
