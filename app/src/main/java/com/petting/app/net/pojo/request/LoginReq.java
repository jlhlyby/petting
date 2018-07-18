package com.petting.app.net.pojo.request;

import java.io.Serializable;

/**
 * Created by yuboyang on 18/7/17.
 */

public class LoginReq implements Serializable {
    public String phone;

    public String getPhone() {
        return phone;
    }

    public LoginReq setPhone(String phone) {
        this.phone = phone;
        return this;
    }
}
