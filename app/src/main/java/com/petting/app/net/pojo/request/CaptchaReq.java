package com.petting.app.net.pojo.request;

import java.io.Serializable;

/**
 * Created by yuboyang on 18/7/17.
 */

public class CaptchaReq implements Serializable {
    public String phone;
    public CaptchaReq setPhone(String phone) {
        this.phone = phone;
        return this;
    }
}
