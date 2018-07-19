package com.petting.app.net.pojo.response;

import java.io.Serializable;

/**
 * Created by yuboyang on 18/7/16.
 */

public class NetBaseResp<T> implements Serializable {
    public int status;
    public String message;
    public T data;

    @Override
    public String toString() {
        return "NetBaseResp{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
