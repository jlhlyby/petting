package com.petting.app.net.api;


import com.petting.app.net.pojo.request.LoginReq;
import com.petting.app.net.pojo.response.LoginResp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by yuboyang on 18/7/15.
 */

public interface LoginApi {
    @POST("user/captcha")
    Call<LoginResp> getCode(@Body LoginReq req);
}
