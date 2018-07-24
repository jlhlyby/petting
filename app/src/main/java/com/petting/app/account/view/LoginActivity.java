package com.petting.app.account.view;

import android.os.Bundle;

import com.petting.app.account.view.base.AbsBaseLoginFragment;
import com.petting.app.base.BaseFragmentActivity;

public class LoginActivity extends BaseFragmentActivity {
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        transform(null,new InputPhoneFragment());
    }
    public void transform(AbsBaseLoginFragment from,AbsBaseLoginFragment toFragment){
        transform(from,toFragment,from == null ? null : from.getMessenger());
    }
}
