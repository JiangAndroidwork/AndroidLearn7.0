package com.laojiang.androidlearn70.activity.intent;

import android.Manifest;
import android.animation.Animator;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.laojiang.androidlearn70.R;
import com.laojiang.androidlearn70.activity.BaseActivity;
import com.laojiang.androidlearn70.adapter.photopicker.PhotoAdapter;
import com.laojiang.androidlearn70.utils.weight.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cc.dagger.photopicker.PhotoPicker;
import cc.dagger.photopicker.picker.Load;
import cc.dagger.photopicker.picker.PhotoFilter;
import cc.dagger.photopicker.picker.PhotoSelectBuilder;
import cc.dagger.photopicker.utils.ImageCaptureManager;


/**
 * 类介绍（必填）：
 * Created by Jiang on 2017/2/8 16:23.
 */

public class PhotosSelectActivity extends BaseActivity {
    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 101;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.button_no_camera)
    Button buttonNoCamera;
    @BindView(R.id.button_one_photo)
    Button buttonOnePhoto;
    @BindView(R.id.button_photo_gif)
    Button buttonPhotoGif;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.button_photo_visible)
    Button btVisible;
    private PhotoAdapter photoAdapter;
    private ArrayList<String> selectedPhotos = new ArrayList<>();
    private List<String> photos;
    int maxNum = 9;
    int columns = 3;
    private ImageCaptureManager imageCaptureManager;

    @Override
    protected void initSetContentView() {
        super.initSetContentView();
        setContentView(R.layout.activity_photopicker);
    }

    @Override
    protected void initViews() {
        super.initViews();
        tvResult.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));
        photoAdapter = new PhotoAdapter(this, selectedPhotos);
        recyclerView.setAdapter(photoAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (photoAdapter.getItemViewType(position) == PhotoAdapter.TYPE_ADD) {
                    pickImage(true,false,false);

                } else {
                    PhotoPicker.preview()
                            .paths((ArrayList<String>) selectedPhotos)
                            .currentItem(position)
                            .start(PhotosSelectActivity.this);
                }
            }
        }));


    }


    @OnClick({R.id.button, R.id.button_no_camera, R.id.button_one_photo, R.id.button_photo_gif,R.id.button_photo_visible})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                pickImage(true,false,false);
                break;
            case R.id.button_no_camera:
                pickImage(false,false,false);
                break;
            case R.id.button_one_photo:



                break;
            case R.id.button_photo_gif:
                PhotoPicker.preview()
                        .paths((ArrayList<String>) photos)
                        .currentItem(0)
                        .start(PhotosSelectActivity.this);
                break;
            case R.id.button_photo_visible:
                visibleStart();
                break;
        }
    }

    private void visibleStart() {
        int cx = (buttonOnePhoto.getLeft() + buttonOnePhoto.getRight()) / 2;
        int cy = (buttonOnePhoto.getTop() + buttonOnePhoto.getBottom()) / 2;
        int max = Math.max(buttonOnePhoto.getWidth(), buttonOnePhoto.getHeight());
        Animator anim = ViewAnimationUtils.createCircularReveal(buttonOnePhoto,cx,cy,0,max);
        buttonOnePhoto.setVisibility(View.VISIBLE);
        anim.start();
    }

    private void pickImage(boolean showCamera,boolean isSingle,boolean isGif) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN // Permission was added in API Level 16
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
                    getString(R.string.permission_rationale),
                    REQUEST_STORAGE_READ_ACCESS_PERMISSION);
        } else {

            Load load = PhotoPicker.load()
                    .showCamera(showCamera)
                    .filter(PhotoFilter.build().showGif(isGif).minSize(2 * 1024))
                    .gridColumns(columns);

            PhotoSelectBuilder builder;

if (isSingle){
    builder = load.single();
}else {

    builder = load.multi().maxPickSize(maxNum).selectedPaths(selectedPhotos);
}

            builder.start(PhotosSelectActivity.this);
        }
    }
    private void requestPermission(final String permission, String rationale, final int requestCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.permission_dialog_title)
                    .setMessage(rationale)
                    .setPositiveButton(R.string.permission_dialog_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(PhotosSelectActivity.this, new String[]{permission}, requestCode);
                        }
                    })
                    .setNegativeButton(R.string.permission_dialog_cancel, null)
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_STORAGE_READ_ACCESS_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case PhotoPicker.REQUEST_SELECTED:
                if (data != null) {
                    photos = data.getStringArrayListExtra(PhotoPicker.EXTRA_RESULT);
                }
                selectedPhotos.clear();

                if (photos != null) {
                    StringBuilder sb = new StringBuilder();
                    for (String p : photos) {
                        sb.append(p);
                        sb.append("\n");
                    }
                    tvResult.setText(sb.toString());
                selectedPhotos.addAll(photos);
                }
            photoAdapter.notifyDataSetChanged();
                break;
            case PhotoPicker.REQUEST_PREVIEW:
                if (resultCode == RESULT_OK) {
                    ArrayList<String> paths = data.getStringArrayListExtra(PhotoPicker.PATHS);
                    tvResult.setText(paths.toString());
                }
                break;
            case ImageCaptureManager.REQUEST_TAKE_PHOTO:
                if(imageCaptureManager.getCurrentPhotoPath() != null) {
                    imageCaptureManager.galleryAddPic();
                    // 照片地址
                    String imagePaht = imageCaptureManager.getCurrentPhotoPath();
                    // ...
                    tvResult.setText(imagePaht.toString());
                }
                break;
        }


    }


}
