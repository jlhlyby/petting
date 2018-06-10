package com.petting.app.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.petting.app.R;
import com.petting.app.tools.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by HUASHAN on 2017/11/21.
 */
public class CareDetailActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_detail);
        ButterKnife.bind(this);
        findViewById(R.id.acd_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.acd_back://返回
                finish();
                break;
        }
    }
}
