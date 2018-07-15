package com.petting.app.base;

import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by JiangXiongPing on 2017/11/16.
 */

public abstract class BaseFragment extends Fragment {
    protected View view = null;
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }
    }
    protected abstract void initView(View root);
    protected abstract void initClick();
}
