package com.petting.app.tools;

import android.app.Dialog;
import android.os.Environment;

/**
 * Created by HUASHAN on 2017/11/25.
 */
public class Contents {
    public static final String IP = "http://47.98.139.72/";
    /**
     * 对话框
     */
    public static Dialog mDialog;
    /**
     * 照片缓存路径
     */
    public static final String CAMERA_PATH=Environment.getExternalStorageDirectory().toString()+"/petting/camera/";
}
