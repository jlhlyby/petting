package com.petting.app.account.model;

import java.io.Serializable;

/**
 * Created by yuboyang on 18/7/22.
 */

public class LoginFragmentMessenger implements Serializable,Cloneable {

    private String phone;
    private String sessionId;

    public LoginFragmentMessenger cloneObj() {
        try {
            return (LoginFragmentMessenger)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return new LoginFragmentMessenger();
    }

    public String getPhone() {
        return phone;
    }

    public LoginFragmentMessenger setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getSessionId() {
        return sessionId;
    }

    public LoginFragmentMessenger setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }
}
