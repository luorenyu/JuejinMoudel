package com.timen4.ronnny.refreshlistview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.timen4.ronnny.refreshlistview.bean.NewsItem;

import java.util.List;

/**
 * Created by luore on 2016/8/21.
 */
public class ViewHolderAdapter extends BaseAdapter {

    private final Context mContext;
    private List<NewsItem> mDates;

    public ViewHolderAdapter(Context mcontext, List<NewsItem> newsItems) {
        this.mContext=mcontext;
        this.mDates =newsItems;
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
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView=View.inflate(mContext,R.layout.lv_item,null);
            viewHolder.iamge= (ImageView) convertView.findViewById(R.id.iv_image);
            viewHolder.tv_articleInfo= (TextView) convertView.findViewById(R.id.tv_info);
            viewHolder.tv_title= (TextView) convertView.findViewById(R.id.tv_title);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }

        viewHolder.iamge.setImageResource(item.getImage());
        viewHolder.tv_title.setText(item.getTitle());
        viewHolder.tv_articleInfo.setText(item.getArtcileInfo());

        return convertView;
    }

    public void addDate(List<NewsItem> newsItems) {
        if(mDates==null){
            this.mDates =newsItems;
        }else{
            mDates.addAll(0,newsItems);
        }
    }

    public class ViewHolder{
        ImageView iamge;
        TextView tv_title;
        TextView tv_articleInfo;
    }

}
