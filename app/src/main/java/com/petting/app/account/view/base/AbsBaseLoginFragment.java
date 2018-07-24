package com.petting.app.account.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.petting.app.account.model.LoginFragmentMessenger;
import com.petting.app.base.AbsBaseFragment;
import com.petting.app.tools.Contents;

/**
 * Created by yuboyang on 18/7/22.
 */

public abstract class AbsBaseLoginFragment extends AbsBaseFragment {
    protected LoginFragmentMessenger messenger;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMessenger();
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
}
