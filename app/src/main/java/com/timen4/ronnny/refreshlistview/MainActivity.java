package com.timen4.ronnny.refreshlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.timen4.ronnny.refreshlistview.bean.NewsItem;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView mlistView;
    private LinearLayout mBottom_bar;
    private LinearLayout mHead_bar;
    private ViewHolderAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mlistView = (ListView) findViewById(R.id.listView);
        mBottom_bar = (LinearLayout) findViewById(R.id.bottom_bar);
        mHead_bar= (LinearLayout) findViewById(R.id.head_bar);
        List<NewsItem> dates = null;
        mAdapter = new ViewHolderAdapter(this,dates);
        mlistView.setAdapter(mAdapter);
        View emptyView = View.inflate(this, R.layout.empty_view, null);
        Button emptyFresh = (Button) emptyView.findViewById(R.id.empty_fresh);
        emptyFresh.setOnClickListener(this);
        mlistView.setEmptyView(emptyView);
        mlistView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.empty_fresh:
                refreshDate();
                break;

        }


    }

    private void refreshDate() {
        List<NewsItem> newsItems=null;
//        newsItems.add(new NewsItem());
        mAdapter.setDate(newsItems);
    }
}
