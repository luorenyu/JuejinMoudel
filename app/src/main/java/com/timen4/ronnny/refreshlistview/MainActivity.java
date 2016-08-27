package com.timen4.ronnny.refreshlistview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.timen4.ronnny.refreshlistview.bean.NewsItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mlistView;
    private LinearLayout mBottom_bar;
    private LinearLayout mHead_bar;
    private ViewHolderAdapter mAdapter;
    private SwipeRefreshLayout mSr_refresh;
    private Button mEmptyFresh;
    int lastFirstVisibleItem = 0;
    private boolean isRunning = false;
    private boolean mIsShow = true;

    private static  int mTouchSlop = 0;
    private ObjectAnimator mHeaderAnimator;
    private ObjectAnimator mBottomAnimator;
    private ImageButton mIb_explore;
    private ArrayList<Integer> imgs=new ArrayList<>();
    private List<NewsItem> mDates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        bindEvents();
    }

    private void initView() {

        mSr_refresh = (SwipeRefreshLayout) findViewById(R.id.sr_refresh);
        mlistView = (ListView) findViewById(R.id.listView);
        mBottom_bar = (LinearLayout) findViewById(R.id.bottom_bar);
        mHead_bar = (LinearLayout) findViewById(R.id.head_bar);
        mTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();

        mEmptyFresh = (Button) findViewById(R.id.empty_fresh);
        mIb_explore = (ImageButton) findViewById(R.id.ib_explore);
        mIb_explore.setImageResource(R.drawable.tab_explore);
//
        View view = LayoutInflater.from(this).inflate(R.layout.lv_headerview, mlistView, false);
        mlistView.addHeaderView(view);


        mAdapter = new ViewHolderAdapter(this, mDates);
        mlistView.setAdapter(mAdapter);

        //设置下拉刷新加载圈的颜色
        mSr_refresh.setColorSchemeColors(getResources().getColor(R.color.colorPrimaryDark), getResources().getColor(R.color.colorAccent));
        //设置下拉加载圈出现距离顶部的位置
        mSr_refresh.setDistanceToTriggerSync(getResources().getDimensionPixelOffset(R.dimen.swipe_progress_appear_offset));
        //设置下拉加载圈转动时距离顶部的位置
        mSr_refresh.setProgressViewEndTarget(true, getResources().getDimensionPixelOffset(R.dimen.swipe_progress_to_top));

    }

    private void initData() {
        imgs.add(R.drawable.img1);
        imgs.add(R.drawable.img2);
        imgs.add(R.drawable.img3);
        imgs.add(R.drawable.img4);
        imgs.add(R.drawable.img5);
        imgs.add(R.drawable.img6);
        imgs.add(R.drawable.img7);
        imgs.add(R.drawable.img8);
        imgs.add(R.drawable.img9);
        imgs.add(R.drawable.img10);
        mDates = refreshDate();
    }

    private void bindEvents() {
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
                                newsItem= new NewsItem(imgs.get((int)(Math.random()*10)), "NewTitle" + i, (int) (Math.random() * 100), "贤榆的鱼" + i, System.currentTimeMillis(), true);
                            }else{
                                newsItem = new NewsItem(imgs.get((int)(Math.random()*10)), "NewTitle" + i, (int) (Math.random() * 100), "xianyu" + i, System.currentTimeMillis(), false);
                            }
                            newsItems.add(newsItem);
                        }
                        mAdapter.addDate(newsItems);
                        mAdapter.notifyDataSetChanged();
                        //调用该方法结束刷新，否则加载圈会一直在
                        mSr_refresh.setRefreshing(false);
                    }
                },1000);
            }
        });
        //第一种方法实现隐藏显示布局
        //该方法布局在显示和隐藏的敏感度没有第二种房是好！
        mlistView.setOnTouchListener(new View.OnTouchListener() {

            private float mEndY;
            private float mStartY;
            private int direction;//0表示向上，1表示向下

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        mStartY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mEndY = event.getY();
                        float v1 = mEndY - mStartY;

                        if (v1 > 3 && isRunning == false && direction == 1) {
                            direction = 0;
                            showBar();
                            mStartY = mEndY;
                            Log.e("renyu", "显示" + mEndY + "");
                            return false;
                        } else if (v1 < -3 && isRunning == false && direction == 0) {
                            direction = 1;
                            hideBar();
                            mStartY = mEndY;
                            Log.e("renyu", "隐藏" + mEndY + "");
                            return false;
                        }
                        mStartY = mEndY;

                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });
        //第二种方法实现隐藏显示布局,但是这种方法实现之后，headbar和bottombar的敏感度并不是很好
//        mlistView.setOnScrollListener(new OnScrollListener(){
//
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//
//
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//
//                if (firstVisibleItem>lastFirstVisibleItem&&isRunning){
//                    showBar();
//                }
//                if(firstVisibleItem<lastFirstVisibleItem&&isRunning){
//                    hideBar();
//                }
//
//                lastFirstVisibleItem=firstVisibleItem;
//
//            }
//        });
    }

    public void hideBar() {
        mHeaderAnimator = ObjectAnimator.ofFloat(mHead_bar, "translationY", -mHead_bar.getHeight());
        mBottomAnimator = ObjectAnimator.ofFloat(mBottom_bar, "translationY", mBottom_bar.getHeight());
        mHeaderAnimator.setDuration(300).start();
        mBottomAnimator.setDuration(300).start();
        mHeaderAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                isRunning = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isRunning = false;
            }

        });
    }

    public void showBar() {
        mHeaderAnimator = ObjectAnimator.ofFloat(mHead_bar, "translationY", 0);
        mBottomAnimator = ObjectAnimator.ofFloat(mBottom_bar, "translationY", 0);

        mHeaderAnimator.setDuration(300).start();
        mBottomAnimator.setDuration(300).start();
    }


    private List<NewsItem> refreshDate() {
        List<NewsItem> newsItems=new ArrayList<>();
        for (int i=0;i<20;i++){
            NewsItem newsItem;
            if(i%2==0){
                newsItem= new NewsItem(imgs.get((int)(Math.random()*10)), "Title" + i, (int) (Math.random() * 100), "xianyu" + i, System.currentTimeMillis(), true);
            }else{
                newsItem = new NewsItem(imgs.get((int)(Math.random()*10)), "Title" + i, (int) (Math.random() * 100), "xianyu" + i, System.currentTimeMillis(), false);
            }
            newsItems.add(newsItem);
        }
        return newsItems;
    }






}
