package com.petting.app.net.pojo.response;

import java.io.Serializable;

/**
 * Created by yuboyang on 18/7/16.
 */

public class NetBaseResp implements Serializable {
    int status;
    String message;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public NetBaseResp setStatus(int status) {
        this.status = status;
        return this;
    }

    public NetBaseResp setMessage(String message) {
        this.message = message;
        return this;
    }
}
