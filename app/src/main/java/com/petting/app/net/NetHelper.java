package com.petting.app.net;

import com.petting.app.net.api.LoginApi;
import com.petting.app.net.pojo.request.LoginReq;
import com.petting.app.net.pojo.response.LoginResp;
import com.petting.app.tools.Contents;

import retrofit2.Callback;
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
    public static void getCode(Callback<LoginResp> callBack){
        LoginReq req = new LoginReq();
        req.setPhone("13766858497");
        getServer(LoginApi.class).getCode(req).enqueue(callBack);
    }
    private static  <T> T getServer(Class<T> tClass){
        return getRetrofit().create(tClass);
    }
}
