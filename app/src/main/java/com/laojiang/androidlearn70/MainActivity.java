package com.laojiang.androidlearn70;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.laojiang.androidlearn70.activity.FruitActivity;
import com.laojiang.androidlearn70.adapter.FruitAdapter;
import com.laojiang.androidlearn70.bean.Fruit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    private DrawerLayout mDrawerLayout;
    private NavigationView navView;
    private View headerView;
    private FloatingActionButton actionButton;
    private Fruit[] fruits = {new Fruit("Apple", R.drawable.icon_food_1), new Fruit("Banana", R.drawable.icon_food_2),
            new Fruit("Pear", R.drawable.icon_food_3
            )};
    private List<Fruit> fruitList = new ArrayList<>();
    private FruitAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFruits();
        initView();
        initSwip();

    }

    private void initSwip() {
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFuits();
            }
        });
    }

    private void refreshFuits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(200);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFruits();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initFruits() {
        fruitList.clear();
        for (int i = 0; i < 200; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }

    private void initView() {
        //利用Toolbar代替 actionbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.nav_view);
        //获取headLayout的实例
        View headerView = navView.getHeaderView(0);
        CircleImageView ivTouXiang = (CircleImageView) headerView.findViewById(R.id.iv_touxiang);
        ivTouXiang.setImageResource(R.mipmap.ico_homework_1);
        //设置标题栏滑动菜单按钮
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_title_menu);

        }
        //设置滑动菜单里面的默认选中条目
        navView.setCheckedItem(R.id.nav_1);
        //滑动菜单条目点击事件
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_1://选项1
                        Toast.makeText(MainActivity.this, "选项1", Toast.LENGTH_SHORT).show();

                        break;
                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        actionButton = (FloatingActionButton) findViewById(R.id.fab);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "是否删除", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this, "悬浮按钮", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        adapter = new FruitAdapter(this, fruitList, new FruitAdapter.CallBack() {
            @Override
            public void callBackOnclick(View v) {
                Toast.makeText(MainActivity.this,"我是条目-=="+(int)v.getTag(),Toast.LENGTH_SHORT).show();
//                fruitList.remove((int)v.getTag());
//                adapter.notifyItemRemoved((int)v.getTag());
                Intent intent = new Intent(MainActivity.this, FruitActivity.class);
                intent.putExtra("fruitName",fruitList.get((int)v.getTag()).getName());
                intent.putExtra("fruitId",fruitList.get((int)v.getTag()).getImageId());
                startActivity(intent);
            }

            @Override
            public void callBackLongOnclick(final View v) {
                Snackbar.make(v,"是否删除",Snackbar.LENGTH_SHORT)
                        .setAction("删除", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                fruitList.remove((int)v.getTag());
                adapter.notifyItemRemoved((int)v.getTag());
                            }
                        }).show();
            }
        });
        recyclerView.setAdapter(adapter);
    }
    //调用toolbar

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home://滑动菜单按钮 id固定
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;

            case R.id.icon_1:
                Toast.makeText(this, "1", Toast.LENGTH_LONG).show();
                break;
            case R.id.icon_2:
                Toast.makeText(this, "2", Toast.LENGTH_LONG).show();
                break;
            case R.id.icon_3:
                Toast.makeText(this, "3", Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }
}
