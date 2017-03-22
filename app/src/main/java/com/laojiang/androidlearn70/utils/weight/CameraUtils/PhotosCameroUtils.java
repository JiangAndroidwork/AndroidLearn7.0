package com.laojiang.androidlearn70.utils.weight.CameraUtils;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.MediaStore;

/**
 * 类介绍（必填）：
 * Created by Jiang on 2017/3/16 16:57.
 */

public class PhotosCameroUtils {
    private Context context;

    public PhotosCameroUtils(Context context) {
        this.context = context;
    }

    public void initCamreo(){
        Intent intent;
        if(Build.VERSION.SDK_INT< 19)
        {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", 80);
            intent.putExtra("outputY", 80);
            intent.putExtra("return-data", true);
        } else

        {
            intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        }

        //(Activity)context.startActivityForResult(intent, 11);
    }

}

