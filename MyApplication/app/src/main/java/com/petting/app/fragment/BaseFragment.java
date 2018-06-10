package com.petting.app.fragment;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by JiangXiongPing on 2017/11/16.
 */

public class BaseFragment extends Fragment {
    protected View view = null;
    protected SharedPreferences sp;

    @Override
    public void onDestroyView() {
        // TODO Auto-generated method stub
        super.onDestroyView();
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }
    }
}
