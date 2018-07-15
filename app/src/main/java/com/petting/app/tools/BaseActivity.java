package com.petting.app.tools;

import com.zhy.autolayout.AutoLayoutActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by zongs on 2017/11/13.
 */

public class BaseActivity extends AutoLayoutActivity implements View.OnClickListener {
    protected SharedPreferences sp;
    protected Context mContext;
    protected Thread mThread;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    @Override
    public void onClick(View v) {

    }
}
