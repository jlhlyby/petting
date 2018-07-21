package com.petting.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.petting.app.R;

/**
 * Created by yuboyang on 18/7/19.
 */

public class PetTitleBar extends RelativeLayout {
    public PetTitleBar(Context context) {
        super(context);
        initView(context);
    }

    public PetTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public PetTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }
    private void initView(Context context) {
        View root = LayoutInflater.from(context).inflate(R.layout.v_title_bar,this);
    }
}
