package com.timen4.ronnny.JueJinModel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.timen4.ronnny.JueJinModel.bean.NewsItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luore on 2016/8/21.
 */
public class ViewHolderAdapter extends BaseAdapter {

    private final Context mContext;
    private List<NewsItem> mDates=new ArrayList<>();
    private static final int TITLE=0;
    private static final int CONTENT=1;

    public ViewHolderAdapter(Context mcontext, List<NewsItem> newsItems) {
        this.mContext=mcontext;
        //添加分类的标题
        mDates.add(new NewsItem("热门文章"));
        mDates.addAll(1,newsItems);
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return TITLE;
        }else{
            return CONTENT;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
            return mDates==null?0:mDates.size();
    }

    @Override
    public NewsItem getItem(int position) {
        return mDates.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsItem item = getItem(position);
        ContentViewHolder contentViewHolder;
        TieleViewHolder tieleViewHolder;
        int type = getItemViewType(position);
        if(convertView==null){
            switch (type){
                case TITLE:
                    tieleViewHolder=new TieleViewHolder();
                    convertView=View.inflate(mContext,R.layout.lv_item_title,null);
                    tieleViewHolder.tv_title= (TextView) convertView.findViewById(R.id.tv_top_title);
                    tieleViewHolder.tv_title.setText(item.getTitle());
                    convertView.setTag(tieleViewHolder);
                    break;
                case CONTENT:
                    contentViewHolder=new ContentViewHolder();
                    convertView=View.inflate(mContext,R.layout.lv_item,null);
                    contentViewHolder.iamge= (ImageView) convertView.findViewById(R.id.iv_image);
                    contentViewHolder.tv_articleInfo= (TextView) convertView.findViewById(R.id.tv_info);
                    contentViewHolder.tv_title= (TextView) convertView.findViewById(R.id.tv_title);

                    contentViewHolder.iamge.setImageResource(item.getImage());
                    contentViewHolder.tv_title.setText(item.getTitle());
                    contentViewHolder.tv_articleInfo.setText(item.getArtcileInfo());
                    convertView.setTag(contentViewHolder);


                    break;
            }

        }else{
            switch (type){
                case TITLE:
                    tieleViewHolder= (TieleViewHolder) convertView.getTag();
                    tieleViewHolder.tv_title.setText(item.getTitle());
                    break;
                case CONTENT:
                    contentViewHolder= (ContentViewHolder) convertView.getTag();
                    contentViewHolder.iamge.setImageResource(item.getImage());
                    contentViewHolder.tv_title.setText(item.getTitle());
                    contentViewHolder.tv_articleInfo.setText(item.getArtcileInfo());
            }
        }



        return convertView;
    }

    public void addDate(List<NewsItem> newsItems) {
        if(mDates==null){
            this.mDates =newsItems;
        }else{
            mDates.addAll(1,newsItems);
        }
    }

    public class ContentViewHolder {
        ImageView iamge;
        TextView tv_title;
        TextView tv_articleInfo;
    }
    public class TieleViewHolder {
        TextView tv_title;
    }

}
