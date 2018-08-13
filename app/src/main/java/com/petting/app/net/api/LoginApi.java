package com.petting.app.net.api;


import com.petting.app.net.pojo.request.CaptchaReq;
import com.petting.app.net.pojo.request.CheckReq;
import com.petting.app.net.pojo.request.PassReq;
import com.petting.app.net.pojo.response.CaptchaRespData;
import com.petting.app.net.pojo.response.CheckRespData;
import com.petting.app.net.pojo.response.NetBaseResp;
import com.petting.app.net.pojo.response.PassRespData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by yuboyang on 18/7/15.
 */

public interface LoginApi {
    /**
     * 获取短信
     * @param req
     * @return
     */
    @POST("user/captcha")
    Call<NetBaseResp<CaptchaRespData>> getCode(@Body CaptchaReq req);

    /**
     * 验证短信
     * @param req
     * @return
     */
    @POST("/user/captcha/check")
    Call<NetBaseResp<CheckRespData>> check(@Body CheckReq req);

    /**
     * 设置密码
     * @param req
     * @return
     */
    @POST("/user/pass")
    Call<NetBaseResp<PassRespData>> pass(@Body PassReq req);
}
