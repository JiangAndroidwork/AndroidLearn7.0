package com.laojiang.androidlearn70;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private NavigationView navView;
    private View headerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.nav_view);
        //获取headLayout的实例

        View headerView = navView.getHeaderView(0);
        CircleImageView ivTouXiang = (CircleImageView) headerView.findViewById(R.id.iv_touxiang);
        ivTouXiang.setImageResource(R.mipmap.ico_homework_1);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_title_menu);

        }
        navView.setCheckedItem(R.id.nav_1);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_1://选项1
                        Toast.makeText(MainActivity.this,"选项1",Toast.LENGTH_SHORT).show();

                        break;
                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

    }
    //调用toolbar

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
               mDrawerLayout.openDrawer(GravityCompat.START);
                break;

            case R.id.icon_1:
                Toast.makeText(this,"1",Toast.LENGTH_LONG).show();
                break;
            case R.id.icon_2:
                Toast.makeText(this,"2",Toast.LENGTH_LONG).show();
                break;
            case R.id.icon_3:
                Toast.makeText(this,"3",Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }
}
