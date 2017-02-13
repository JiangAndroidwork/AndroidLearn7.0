package com.laojiang.androidlearn70.activity.intent;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.foamtrace.photopicker.ImageCaptureManager;
import com.foamtrace.photopicker.PhotoPickerActivity;
import com.foamtrace.photopicker.SelectModel;
import com.foamtrace.photopicker.intent.PhotoPickerIntent;
import com.laojiang.androidlearn70.R;
import com.laojiang.androidlearn70.activity.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 类介绍（必填）：
 * Created by Jiang on 2017/2/9 8:30.
 */

public class PhotosTest2Activity extends BaseActivity {
    private static final int REQUEST_CAMERA_CODE = 123;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.button_no_camera)
    Button buttonNoCamera;
    @BindView(R.id.button_one_photo)
    Button buttonOnePhoto;
    @BindView(R.id.button_photo_gif)
    Button buttonPhotoGif;
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
ArrayList<String> selectPahts = new ArrayList<>();
    @Override
    protected void initSetContentView() {
        super.initSetContentView();
        setContentView(R.layout.activity_photopicker);
    }

    @Override
    protected void initViews() {
        super.initViews();
    }



    @OnClick({R.id.button, R.id.button_no_camera, R.id.button_one_photo, R.id.button_photo_gif})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                PhotoPickerIntent intent = new PhotoPickerIntent(PhotosTest2Activity.this);
                intent.setSelectModel(SelectModel.MULTI);
                intent.setShowCarema(true); // 是否显示拍照， 默认false
                intent.setMaxTotal(9); // 最多选择照片数量，默认为9
                intent.setSelectedPaths(selectPahts); // 已选中的照片地址， 用于回显选中状态
// intent.setImageConfig(config);
                startActivityForResult(intent, REQUEST_CAMERA_CODE);
                break;
            case R.id.button_no_camera:
                break;
            case R.id.button_one_photo:
                break;
            case R.id.button_photo_gif:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                // 选择照片
                case REQUEST_CAMERA_CODE:
                    refreshAdpater(data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT));
                    break;
                // 拍照
                case ImageCaptureManager.REQUEST_TAKE_PHOTO:
//                    if(captureManager.getCurrentPhotoPath() != null) {
//                        captureManager.galleryAddPic();
//                        // 照片地址
//                        String imagePaht = captureManager.getCurrentPhotoPath();
//                        // ...
//                    }
                    break;
                // 预览
//                case REQUEST_PREVIEW_CODE:
//                    refreshAdpater(data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT));
//                    break;
            }
        }
    }
    private void refreshAdpater(ArrayList<String> paths){
        // 处理返回照片地址
        tvResult.setText(paths.toString());
    }
}
