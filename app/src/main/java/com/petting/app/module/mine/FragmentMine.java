package com.petting.app.module.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.petting.app.R;
import com.petting.app.net.pojo.response.LoginResp;
import com.petting.app.net.NetHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by JiangXiongPing on 2017/11/14.
 */

public class FragmentMine extends Fragment {
    private static final String TAG = "FragmentMine";

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        view.findViewById(R.id.f_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo 发送登录请求
                NetHelper.getCode(new Callback<LoginResp>() {
                    @Override
                    public void onResponse(Call<LoginResp> call, Response<LoginResp> response) {

                        Log.i(TAG, "onResponse: "+response.body());
                    }

                    @Override
                    public void onFailure(Call<LoginResp> call, Throwable t) {
                        Log.i(TAG, "onFailure: "+t);
                    }
                });
            }
        });
        return view;
    }
}
