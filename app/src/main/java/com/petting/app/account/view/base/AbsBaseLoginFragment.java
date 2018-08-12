package com.petting.app.account.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import com.petting.app.R;
import com.petting.app.account.model.LoginFragmentMessenger;
import com.petting.app.account.view.LoginActivity;
import com.petting.app.base.AbsBaseFragment;
import com.petting.app.tools.Contents;

/**
 * Created by yuboyang on 18/7/22.
 */

public abstract class AbsBaseLoginFragment extends AbsBaseFragment {
    LoginActivity mActivity;
    /**
     * fragment直接数据传输
     */
    protected LoginFragmentMessenger messenger;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (LoginActivity) getActivity();
        initMessenger();
    }
    @Override
    protected View getRootView(LayoutInflater inflater, @Nullable ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_login_base, container, false);
        inflater.inflate(getLayoutRes(), (ScrollView) view.findViewById(R.id.sv_content), true);
        return view;
    }

    public LoginFragmentMessenger getMessenger() {
        if (messenger == null) {
            initMessenger();
        }
        return messenger;
    }

    /**
     * 初始化数据
     */
    protected void initMessenger() {
        Bundle bundle = getArguments();
        LoginFragmentMessenger preMessenger = null;
        if (bundle != null) {
            preMessenger = (LoginFragmentMessenger) bundle.getSerializable(Contents.KEY_FRAGMENT_MESSENGER);
        }
        if (preMessenger != null) {
            messenger = preMessenger.cloneObj();
        }
        if (messenger == null) {
            messenger = new LoginFragmentMessenger();
        }
    }
    protected void showShortToast(String msg){
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
    }
    protected void showLongToast(String msg){
        Toast.makeText(getContext(),msg,Toast.LENGTH_LONG).show();
    }

    /**
     * 跳转
     * @param from
     * @param toFragment
     */
    public void transform(AbsBaseLoginFragment from,AbsBaseLoginFragment toFragment){
       mActivity.transform(from,toFragment);
    }
}
