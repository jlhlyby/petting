package com.petting.app.net.pojo.request;

import java.io.Serializable;

/**
 * Created by yuboyang on 18/7/17.
 */

public class CheckReq implements Serializable {
    public String sessionId;
    public String captcha;

    public CheckReq setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public CheckReq setCaptcha(String captcha) {
        this.captcha = captcha;
        return this;
    }
}
