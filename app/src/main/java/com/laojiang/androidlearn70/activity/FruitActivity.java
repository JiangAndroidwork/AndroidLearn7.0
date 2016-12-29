package com.laojiang.androidlearn70.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.laojiang.androidlearn70.R;
import com.laojiang.androidlearn70.adapter.fruitdetail.FruitDetailAdapter;
import com.laojiang.androidlearn70.adapter.fruitdetail.ViewPagerAdapter;
import com.laojiang.androidlearn70.utils.view.AppBarStateChangeListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jiang on 2016/12/21.
 */

public class FruitActivity extends AppCompatActivity {
    private static final String ZHANKAI = "zhankai";
    private static final String ZHEDIE = "zhedie";
    private static final String ZHONGJIAN = "zhongjian";
    @BindView(R.id.fruit_image_view)
    ImageView fruitImageView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    //    @BindView(R.id.fruit_content_text)
//    TextView fruitContentText;
//    @BindView(R.id.fab)
//    FloatingActionButton fab;
    @BindView(R.id.video_detail_linear_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;


    private String fruitName;
    private int fruitId;
    private FruitDetailAdapter adapter;
    private List<String> list;
    private String appBarState;
    private ViewPagerAdapter viewPagerAdapter;
    private LayoutInflater layoutInflater;
    private String[] tabs = new String[]{"标题1", "标题2", "标题3"};
    private AppBarLayout.LayoutParams layoutParams;
    private TestViewPageAdapter viewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);
        ButterKnife.bind(this);
        layoutInflater = LayoutInflater.from(this);
        initView();
        initData();
        initListner();
    }

    private void initView() {
        Intent intent = getIntent();
        fruitName = intent.getStringExtra("fruitName");
        fruitId = intent.getIntExtra("fruitId", 0);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        collapsingToolbar.setTitle(fruitName);
        Glide.with(this).load(fruitId).into(fruitImageView);
        String fruitContent = generateFruitContent(fruitName);
//        fruitContentText.setText(fruitContent);
        list = new ArrayList<>();

        adapter = new FruitDetailAdapter(this, list);
//        lvData.setAdapter(adapter);
        /**
         * 设置是否可以将collapsingTollbar推上去 加上下面代码是可以推上去
         */
//        layoutParams = (AppBarLayout.LayoutParams) collapsingToolbar.getLayoutParams();
//        layoutParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
//                | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
//        collapsingToolbar.setLayoutParams(layoutParams);//这句必须加上

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),tabs,this);
        viewAdapter = new TestViewPageAdapter();
        viewPager.setAdapter(viewAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private View getTabItemView(int index) {
        View view = layoutInflater.inflate(R.layout.tabview, null);
        TextView textView = (TextView) view.findViewById(R.id.tabname);
        textView.setText(tabs[index]);
        return view;
    }

    private void initListner() {
        /**
         * 标题栏折叠展开的监听事件
         */
        appBar.addOnOffsetChangedListener(new AppBarStateChangeListener() {


            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.EXPANDED) {
                    //展开状态
                    appBarState = ZHANKAI;
                } else if (state == State.COLLAPSED) {
                    //折叠状态
                    appBarState = ZHEDIE;

                } else {
                    //中间状态
                    appBarState = ZHONGJIAN;
                }

            }
        });



    }

    private void initData() {
        String[] str = new String[]{"还有谁", "是不是傻", "你说的没错", "恩对的0"};

        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int num = random.nextInt(str.length);
            list.add(str[num]);
            adapter.notifyDataSetChanged();
        }
    }

    private String generateFruitContent(String fruitName) {
        StringBuilder fruitCotent = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            fruitCotent.append(fruitName);
        }

        return fruitCotent.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

//    @OnClick(R.id.fab)
//    public void onClick() {
//
//        Toast.makeText(FruitActivity.this, "项目按钮", Toast.LENGTH_SHORT).show();
//    }
    class TestViewPageAdapter extends PagerAdapter {
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView textView = new TextView(FruitActivity.this);
            textView.setGravity(Gravity.CENTER);
            textView.setText("pager "+(position+1));
            textView.setTextSize(30);
            textView.setTextColor(Color.BLUE);
            container.addView(textView);
            return textView;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
        @Override
        public int getCount() {
            return 5;
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
        /*获得标题*/
        /*该方法必须写,不然tablayout不能显示标题*/
        @Override
        public CharSequence getPageTitle(int position) {
            return "TAB"+(position+1);
        }
    }

}
