package com.petting.app.account.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.petting.app.R;
import com.petting.app.net.NetHelper;
import com.petting.app.net.pojo.response.CaptchaRespData;
import com.petting.app.net.pojo.response.NetBaseResp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends Activity {
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        NetHelper.getCode(new Callback<NetBaseResp<CaptchaRespData>>() {
            @Override
            public void onResponse(Call<NetBaseResp<CaptchaRespData>> call, Response<NetBaseResp<CaptchaRespData>> response) {
                Log.i(TAG, "onResponse: "+response.body().toString());
            }

            @Override
            public void onFailure(Call<NetBaseResp<CaptchaRespData>> call, Throwable t) {
                Log.i(TAG, "onFailure: "+t);
            }
        });
    }
}
