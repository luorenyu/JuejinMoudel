package com.timen4.ronnny.refreshlistview;

import android.animation.ObjectAnimator;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ViewUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.timen4.ronnny.refreshlistview.bean.NewsItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView mlistView;
    private LinearLayout mBottom_bar;
    private LinearLayout mHead_bar;
    private ViewHolderAdapter mAdapter;
    private SwipeRefreshLayout mSr_refresh;
    private Button mEmptyFresh;

    private static  int mTouchSlop = 0;
    private ObjectAnimator mHeaderAnimator;
    private ObjectAnimator mBottomAnimator;

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
        mTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();
        List<NewsItem> dates = null;
        mEmptyFresh = (Button) findViewById(R.id.empty_fresh);
//        mlistView.setEmptyView(mEmptyFresh);
        mAdapter = new ViewHolderAdapter(this,dates);
        mlistView.setAdapter(mAdapter);
        View view = new View(this);
        ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHead_bar.getHeight());
        view.setLayoutParams(params);
        mlistView.addHeaderView(view);
        refreshDate();
        mSr_refresh = (SwipeRefreshLayout) findViewById(R.id.sr_refresh);
        bindEvents();
        //设置下拉刷新加载圈的颜色
        mSr_refresh.setColorSchemeColors(getResources().getColor(R.color.colorPrimaryDark),getResources().getColor(R.color.colorAccent));
    }

    private void bindEvents() {
        mEmptyFresh.setOnClickListener(this);
        mSr_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSr_refresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List<NewsItem> newsItems=new ArrayList<>();
                        for (int i=mAdapter.getCount()+10;i>mAdapter.getCount();i--){
                            NewsItem newsItem;
                            if(i%2==0){
                                newsItem= new NewsItem(R.mipmap.ic_launcher, "NewTitle" + i, (int) (Math.random() * 100), "贤榆的鱼" + i, System.currentTimeMillis(), true);
                            }else{
                                newsItem = new NewsItem(R.mipmap.ic_launcher, "NewTitle" + i, (int) (Math.random() * 100), "xianyu" + i, System.currentTimeMillis(), false);
                            }
                            newsItems.add(newsItem);
                        }
                        mAdapter.addDate(newsItems);
                        mAdapter.notifyDataSetChanged();
                        //调用该方法结束刷新
                        mSr_refresh.setRefreshing(false);
                    }
                },1000);
            }
        });

        mlistView.setOnTouchListener(new View.OnTouchListener() {

            private float mEndY;
            private float mStartY;


            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        mStartY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mEndY = event.getY();
                        float v1 = mEndY - mStartY;
                        if(v1>mTouchSlop){//下滑
//                            mBottom_bar.setVisibility(View.VISIBLE);
//                            mHead_bar.setVisibility(View.VISIBLE);
                            showBar();
                        }else if(v1<mTouchSlop){//上滑
//                            mBottom_bar.setVisibility(View.GONE);
//                            mHead_bar.setVisibility(View.GONE);
                            hideBar();

                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });
    }

    public void hideBar() {
        if(mHeaderAnimator!=null&&mHeaderAnimator.isRunning()){
            mHeaderAnimator.cancel();
        }
        if(mBottomAnimator!=null&&mBottomAnimator.isRunning()){
            mBottomAnimator.cancel();
        }
        mHeaderAnimator = ObjectAnimator.ofFloat(mHead_bar, "translationY", -mHead_bar.getHeight());
        mBottomAnimator = ObjectAnimator.ofFloat(mBottom_bar, "translationY", mBottom_bar.getHeight());
        mHeaderAnimator.setDuration(200).start();
        mBottomAnimator.setDuration(200).start();
    }

    public void showBar() {
        if(mHeaderAnimator!=null&&mHeaderAnimator.isRunning()){
            mHeaderAnimator.cancel();
        }
        if(mBottomAnimator!=null&&mBottomAnimator.isRunning()){
            mBottomAnimator.cancel();
        }
         mHeaderAnimator = ObjectAnimator.ofFloat(mHead_bar, "translationY", 0);
         mBottomAnimator = ObjectAnimator.ofFloat(mBottom_bar, "translationY", 0);

        mHeaderAnimator.setDuration(200).start();
        mBottomAnimator.setDuration(200).start();
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
        List<NewsItem> newsItems=new ArrayList<>();
        for (int i=0;i<20;i++){
            NewsItem newsItem;
            if(i%2==0){
                newsItem= new NewsItem(R.mipmap.ic_launcher, "Title" + i, (int) (Math.random() * 100), "xianyu" + i, System.currentTimeMillis(), true);
            }else{
                newsItem = new NewsItem(R.mipmap.ic_launcher, "Title" + i, (int) (Math.random() * 100), "xianyu" + i, System.currentTimeMillis(), false);
            }
            newsItems.add(newsItem);
        }
        mAdapter.addDate(newsItems);
        mAdapter.notifyDataSetChanged();
    }






}
