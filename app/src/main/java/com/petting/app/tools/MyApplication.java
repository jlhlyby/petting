package com.petting.app.tools;

import android.app.Application;
import android.util.DisplayMetrics;

import com.petting.app.store.BaseStore;

/**
 * Created by JiangXiongPing on 2017/11/16.
 */

public class MyApplication extends Application {
    private int mScreenWidth;// 屏幕宽度
    private int mScreenHeight;//屏幕高度
    private static MyApplication application;
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        BaseStore.init(this);
        //获取屏幕分辨率
        DisplayMetrics dm = getResources().getDisplayMetrics();
        setmScreenWidth(dm.widthPixels);
        setmScreenHeight(dm.heightPixels);
        //设置全局字体
//        Typeface mTypeface = Typeface.createFromAsset(getAssets(), "fonts/NotoSansJP-Medium.otf");
//
//        try {
//            Field field = Typeface.class.getDeclaredField("MONOSPACE");
//            field.setAccessible(true);
//            field.set(null, mTypeface);
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
    }

    public synchronized static MyApplication getApplication() {
        return application;
    }

    public int getmScreenWidth() {
        return mScreenWidth;
    }

    public void setmScreenWidth(int mScreenWidth) {
        this.mScreenWidth = mScreenWidth;
    }

    public int getmScreenHeight() {
        return mScreenHeight;
    }

    public void setmScreenHeight(int mScreenHeight) {
        this.mScreenHeight = mScreenHeight;
    }
}
