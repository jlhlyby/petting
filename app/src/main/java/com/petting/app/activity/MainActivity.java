package com.petting.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.petting.app.R;
import com.petting.app.fragment.FragmentFind;
import com.petting.app.fragment.FragmentHome;
import com.petting.app.fragment.FragmentMine;
import com.petting.app.fragment.FragmentTip;
import com.petting.app.tools.MyApplication;

public class MainActivity extends FragmentActivity  {
    private FragmentTabHost fragmentTabHost;
    private String texts[] = {"Home", "发现", "", "通知", "我的"};
    private int imageButton[] = {R.drawable.tab_home_selector, R.drawable.tab_find_selector, R.mipmap.tab_add, R.drawable.tab_tip_selector, R.drawable.tab_mine_selector};
    private Class fragmentArray[] = {FragmentHome.class, FragmentFind.class, null, FragmentTip.class, FragmentMine.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 实例化tabhost
        fragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(this, getSupportFragmentManager(),
                R.id.maincontent);
        for (int i = 0; i < texts.length; i++) {
            TabHost.TabSpec spec = fragmentTabHost.newTabSpec(texts[i]).setIndicator(getView(i));

            fragmentTabHost.addTab(spec, fragmentArray[i], null);

            fragmentTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.color.tab_bac_white);
        }
        fragmentTabHost.getTabWidget().getChildAt(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ShareNewsActivity.class));
            }
        });
    }

    private View getView(int i) {
        View view = View.inflate(MainActivity.this, R.layout.tabcontent, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        TextView textView = (TextView) view.findViewById(R.id.text);
        imageView.setImageResource(imageButton[i]);
        if (i == 2) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(imageView.getLayoutParams());
            params.gravity = Gravity.CENTER_HORIZONTAL;
            params.width = (int) (147 * (MyApplication.getInstance().getmScreenWidth() / 1080.00));//cation
            params.height = (int) (147 * (MyApplication.getInstance().getmScreenHeight() / 1920.00));//cation
            imageView.setLayoutParams(params);
        }
        textView.setText(texts[i]);
        if (i == 2) textView.setVisibility(View.GONE);
        return view;
    }


}
