package com.petting.app.account.store;

import android.text.TextUtils;

import com.petting.app.store.BaseStore;

/**
 * Created by yuboyang on 18/8/25.
 */

public class LoginStore extends BaseStore {
    private static final String KEY_TOKEN = " token";
    private static LoginStore sLoginStore = new LoginStore();
    private String token;

    public LoginStore() {
        super("login_info");
    }

    public boolean isLoginNew() {
        return !TextUtils.isEmpty(getToken());
    }

    public void saveToken(String token) {
        this.token = token;
        putString(KEY_TOKEN, token);
    }

    public String getToken() {
        if (token == null) {
            token = getString(KEY_TOKEN, null);
        }
        return token;
    }

    public static LoginStore getIns() {
        return sLoginStore;
    }
}
