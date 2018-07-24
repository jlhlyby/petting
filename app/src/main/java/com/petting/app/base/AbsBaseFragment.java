package com.petting.app.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.petting.app.tools.Logger;


/**
 * Created by JiangXiongPing on 2017/11/16.
 */

public abstract class AbsBaseFragment extends Fragment {
    protected String TAG = getClass().getSimpleName();
    protected View view = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutRes(), container, false);
        initView(view);
        initClick();
        return view;
    }

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
    public void LogI(String msg){
        Logger.i(TAG,msg);
    }

    protected abstract int getLayoutRes();
    protected abstract void initView(View root);
    protected abstract void initClick();
}
