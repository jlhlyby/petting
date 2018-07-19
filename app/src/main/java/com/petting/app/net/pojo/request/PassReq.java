package com.petting.app.net.pojo.request;

import java.io.Serializable;

/**
 * Created by yuboyang on 18/7/17.
 */

public class PassReq implements Serializable {
    public String phone;
    public String password;
    public String sessionId;

    public PassReq setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public PassReq setPassword(String password) {
        this.password = password;
        return this;
    }

    public PassReq setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }
}
