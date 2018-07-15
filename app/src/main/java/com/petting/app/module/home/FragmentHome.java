package com.petting.app.module.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.petting.app.R;
import com.petting.app.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by JiangXiongPing on 2017/11/14.
 */

public class FragmentHome extends BaseFragment {
    ViewPager viewPager;
    TextView cationTv;//关注
    TextView barCation;//关注下方横线
    TextView shareTv;//分享
    TextView barShare;//分享下方横线
    private ArrayList<Fragment> fragmentList;
    private ViewPagerAdapter mViewPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = View.inflate(getActivity(), R.layout.fragment_home, null);
            initView(view);
            initClick();
        }
        return view;
    }

    @Override
    protected void initView(View root) {
        viewPager = (ViewPager) view.findViewById(R.id.fh_vp);
        barCation = (TextView) view.findViewById(R.id.fh_cation);
        barShare = (TextView) view.findViewById(R.id.fh_share);
        cationTv = (TextView) view.findViewById(R.id.fh_cation_text);
        shareTv = (TextView) view.findViewById(R.id.fh_share_text);
        fragmentList = new ArrayList<>();
        fragmentList.add(new FragmentCare());
        fragmentList.add(new FragmentShare());
        viewPager.setAdapter(new ViewPagerAdapter(getActivity().getSupportFragmentManager()));
        viewPager.setCurrentItem(0);
        barCation.setVisibility(View.VISIBLE);
        barShare.setVisibility(View.INVISIBLE);
        cationTv.setSelected(true);
        shareTv.setSelected(false);

    }

    @Override
    protected void initClick() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {//关注
                    barCation.setVisibility(View.VISIBLE);
                    barShare.setVisibility(View.INVISIBLE);
                    cationTv.setSelected(true);
                    shareTv.setSelected(false);
                } else {//下方
                    barCation.setVisibility(View.INVISIBLE);
                    barShare.setVisibility(View.VISIBLE);
                    cationTv.setSelected(false);
                    shareTv.setSelected(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        view.findViewById(R.id.fh_share_text).setOnClickListener(new View.OnClickListener() {//切换到推荐
            @Override
            public void onClick(View v) {
                if (viewPager.getCurrentItem() != 1)
                    viewPager.setCurrentItem(1);
            }
        });
        view.findViewById(R.id.fh_cation_text).setOnClickListener(new View.OnClickListener() {//切换到关注
            @Override
            public void onClick(View v) {
                if (viewPager.getCurrentItem() != 0)
                    viewPager.setCurrentItem(0);
            }
        });
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {


        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
