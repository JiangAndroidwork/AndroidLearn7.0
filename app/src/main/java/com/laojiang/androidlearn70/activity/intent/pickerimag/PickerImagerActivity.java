package com.laojiang.androidlearn70.activity.intent.pickerimag;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.laojiang.androidlearn70.R;
import com.laojiang.androidlearn70.activity.BaseActivity;
import com.laojiang.androidlearn70.adapter.photopicker.PhotoAdapter;
import com.laojiang.androidlearn70.utils.weight.RecyclerItemClickListener;
import com.laojiang.imagepickers.ImagePicker;
import com.laojiang.imagepickers.data.ImageBean;
import com.laojiang.imagepickers.data.ImagePickType;
import com.laojiang.imagepickers.data.ImagePickerOptions;
import com.laojiang.imagepickers.ui.pager.view.ImagePagerActivity;
import com.laojiang.imagepickers.utils.GlideImagePickerDisplayer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类介绍（必填）：
 * Created by Jiang on 2017/4/27 14:22.
 */

public class PickerImagerActivity extends BaseActivity {
    private static final int REQUEST_CODE = 101;
    private static final int RESULT_CODE = 202;

    GridView gvPhotopicker;
    @BindView(R.id.recycler_view_picker)
    RecyclerView recyclerView;
    @BindView(R.id.bt_button)
    Button btButton;
    private String cachePath;
    private PhotoAdapter photoAdapter;
    private List<ImageBean> resultList;
    private ArrayList<ImageBean> selectedPhotos = new ArrayList<>();

    private ImagePickerOptions mOptions;
    private ImagePickerOptions imagePickerOptions;

    @Override
    protected void initSetContentView() {
        super.initSetContentView();
        setContentView(R.layout.activity_picker_imag);
    }

    @Override
    protected void initViews() {
        super.initViews();
        cachePath = Environment.getExternalStorageDirectory() + "/bjhj/file/";
        File file = new File(cachePath);
        if (!file.exists()) {
            file.mkdir();
        }


        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));
        photoAdapter = new PhotoAdapter(this, selectedPhotos);
        recyclerView.setAdapter(photoAdapter);
        /**
         * imagePickerOptions 需要其中的 getMaxNum()方法参数
         */
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (photoAdapter.getItemViewType(position) == PhotoAdapter.TYPE_ADD) {
                    addImage();
                } else {
                    ImagePagerActivity.start(false,PickerImagerActivity.this, selectedPhotos
                            , position, imagePickerOptions);
                }
            }
        }));
    }

    @OnClick(R.id.bt_button)
    public void onClick() {

        addImage();
    }

    private void addImage() {
        ImagePicker build = new ImagePicker.Builder()
                .pickType(ImagePickType.MUTIL) //设置选取类型(拍照ONLY_CAMERA、单选SINGLE、多选MUTIL)
                .maxNum(9) //设置最大选择数量(此选项只对多选生效，拍照和单选都是1，修改后也无效)
                .needCamera(true) //是否需要在界面中显示相机入口(类似微信那样)
                .cachePath(cachePath) //自定义缓存路径(拍照和裁剪都需要用到缓存)
                .doCrop(1, 1, 300, 300) //裁剪功能需要调用这个方法，多选模式下无效，参数：aspectX,aspectY,outputX,outputY
                .displayer(new GlideImagePickerDisplayer()) //自定义图片加载器，默认是Glide实现的,可自定义图片加载器
                .build();
        imagePickerOptions = build.getmOptions();
        build.start(this, REQUEST_CODE, RESULT_CODE); //自定义RequestCode和ResultCode
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CODE && data != null) {
            //获取选择的图片数据
            resultList = data.getParcelableArrayListExtra(ImagePicker.INTENT_RESULT_DATA);
            Log.i("获取到的图片数据===", resultList.toString());
            selectedPhotos.addAll(resultList);
            photoAdapter.notifyDataSetChanged();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
