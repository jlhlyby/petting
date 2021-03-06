package com.petting.app.tools;

import android.app.Dialog;
import android.os.Environment;

/**
 * Created by HUASHAN on 2017/11/25.
 */
public class Contents {
    public static final String IP = "http://47.98.139.72/";
    /**
     * fragment之间数据传输
     */
    public static final String KEY_FRAGMENT_MESSENGER = "key_fragment_messenger";
    /**
     * 对话框
     */
    public static Dialog mDialog;
    /**
     * 照片缓存路径
     */
    public static final String CAMERA_PATH=Environment.getExternalStorageDirectory().toString()+"/petting/camera/";
    /**
     * 网络ok
     */
    public static final int NET_STATUS_OK = 0;
    /**
     * 验证码错误
     */
    public static final int NET_STATUS_CODE_ERROR = 2;
}
