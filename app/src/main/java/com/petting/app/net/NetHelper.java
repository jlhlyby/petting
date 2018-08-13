package com.petting.app.net;

import com.petting.app.net.api.LoginApi;
import com.petting.app.net.pojo.request.CaptchaReq;
import com.petting.app.net.pojo.request.CheckReq;
import com.petting.app.net.pojo.request.PassReq;
import com.petting.app.net.pojo.response.CaptchaRespData;
import com.petting.app.net.pojo.response.NetBaseResp;
import com.petting.app.tools.Contents;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yuboyang on 18/7/15.
 */

public class NetHelper {
    private static Retrofit sRetrofit;
    private static Retrofit getRetrofit(){
        if (sRetrofit == null){
           sRetrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Contents.IP)
                    .build();
        }

        return sRetrofit;
    }
    public static void getCode(CaptchaReq req,NetCallBack<NetBaseResp<CaptchaRespData>> callBack){
        getServer(LoginApi.class).getCode(req).enqueue(callBack);
    }
    public static void check(CheckReq req,NetCallBack<NetBaseResp<CheckReq>> callBack) {
        getServer(LoginApi.class).check(req).enqueue(callBack);
    }
    public static void pass(PassReq req, NetCallBack<NetBaseResp<PassReq>> callBack) {
        getServer(LoginApi.class).pass(req).enqueue(callBack);
    }
    private static  <T> T getServer(Class<T> tClass){
        return getRetrofit().create(tClass);
    }
}
