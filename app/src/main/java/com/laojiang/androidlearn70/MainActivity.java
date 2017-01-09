package com.laojiang.androidlearn70;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.githang.statusbar.StatusBarCompat;
import com.laojiang.androidlearn70.activity.FruitActivity;
import com.laojiang.androidlearn70.activity.PendingIntentActivity;
import com.laojiang.androidlearn70.activity.intent.PriseActivity;
import com.laojiang.androidlearn70.adapter.FruitAdapter;
import com.laojiang.androidlearn70.bean.Fruit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public  class MainActivity extends AppCompatActivity {

    private static final int CHOOSE_PHOTO = 2;
    private static final String TAG = "测试demo";
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
    private CircleImageView ivTouXiang;
    private Uri imageUri;
    private File outputImage;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private int lastVisibleItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //设置状态栏的背景颜色及字体颜色
        StatusBarCompat.setStatusBarColor(this,getResources().getColor(R.color.colorPrimary),false);
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

                break;
            case R.id.icon_2:
                Toast.makeText(this, "2", Toast.LENGTH_LONG).show();
                break;
            case R.id.icon_3:
                Toast.makeText(this, "3", Toast.LENGTH_LONG).show();
                break;
            case R.id.icon_4:
                initNotification();
                break;
        }
        return true;
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
        for (int i = 0; i < 15; i++) {
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
        ivTouXiang = (CircleImageView) headerView.findViewById(R.id.iv_touxiang);
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
                    initPotos();
                        break;
                    case R.id.nav_2://选项2
                        initCaptrue();
                        break;
                    case R.id.nav_3://选项3
                       Intent intent = new Intent(MainActivity.this,PriseActivity.class);
                        startActivity(intent);
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
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        linearLayoutManager = new LinearLayoutManager(this);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(linearLayoutManager);
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
        iniListener();
    }

    private void iniListener() {
        //上拉加载
     recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
         @Override
         public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
             super.onScrollStateChanged(recyclerView, newState);
             if (newState ==RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 ==adapter.getItemCount()) {
                 adapter.changeMoreStatus(FruitAdapter.LOADING_MORE);
                 new Handler().postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         List<Fruit> newDatas = new ArrayList<Fruit>();
                         for (int i = 0; i< 15; i++) {
                             Random random = new Random();
                             int index = random.nextInt(fruits.length);
                             fruitList.add(fruits[index]);
                         }
                         adapter.addMoreItem(newDatas);
                         adapter.changeMoreStatus(FruitAdapter.PULLUP_LOAD_MORE);
                     }
                 }, 2500);
             }
         }

         @Override
         public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
             super.onScrolled(recyclerView, dx, dy);
             lastVisibleItem =linearLayoutManager.findLastVisibleItemPosition();
         }
     });
    }

    /**
     * 照片
     */
    private void initCaptrue() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
        Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

        }else {
            openAlbum();
        }
    }

    /**
     * 打开本地照片
     */
    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PHOTO);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    openAlbum();
                }else {
                    Toast.makeText(this,"需要权限",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    /**
     * 照相
     */
    private void initPotos() {
        outputImage = new File(getExternalCacheDir(),System.currentTimeMillis()+"_output_image.jpg");
        try{
            if (outputImage.exists()){
                outputImage.delete();
            }
            outputImage.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT>=24){
            imageUri = FileProvider.getUriForFile(MainActivity.this,"com.android", outputImage);
        }else {
            imageUri = Uri.fromFile(outputImage);
        }
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(intent,22);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 22:
                if (resultCode==RESULT_OK){
                    String absolutePath = outputImage.getAbsolutePath();
                    Glide.with(this).load(absolutePath).into(ivTouXiang);
                }
                break;
            case CHOOSE_PHOTO:
                if (resultCode==RESULT_OK){
                    if (Build.VERSION.SDK_INT>=19){
                        handleImageOnKitKat(data);
                    }else {
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
        }
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath  = getImagePath(uri,null);
        displayImage(imagePath);
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this,uri)){
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id = docId.split(":")[1];//解析出数字格式的id
                String selection = MediaStore.Images.Media._ID+"="+id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if ("com.android.providers.downloads.documents".equals(uri
            .getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                imagePath = getImagePath(contentUri,null);
            }
        }else if ("content".equalsIgnoreCase(uri.getScheme())){
            imagePath = getImagePath(uri,null);
        }else if ("file".equalsIgnoreCase(uri.getScheme())){
            imagePath = uri.getPath();
        }
        displayImage(imagePath);
    }


    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
        if (cursor!=null){
            if (cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));

            }
            cursor.close();
        }
        return path;
    }
    private void displayImage(String imagePath) {
        Log.d(TAG,"我是图片的路径-==="+imagePath);
        if (imagePath!=null){
            Glide.with(this).load(imagePath).override(400,400).into(ivTouXiang);
        }
    }

    private void initNotification() {
        Intent intents =new Intent(this, PendingIntentActivity.class);
        PendingIntent pi = PendingIntent.getActivities(this,0, new Intent[]{intents},0);


        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("我是标题")
                .setContentText("我是内容")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.icon_title)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.icon_food_1))
                .setContentIntent(pi)//pendingIntent跳转
                .setAutoCancel(true)//点击通知之后自己取消
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(
                        BitmapFactory.decodeResource(getResources(),R.drawable.icon_food_2)))
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .build();
        manager.notify(1,notification);

    }

}
