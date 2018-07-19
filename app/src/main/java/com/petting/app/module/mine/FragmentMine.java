package com.petting.app.module.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.petting.app.R;
import com.petting.app.net.NetHelper;
import com.petting.app.net.pojo.response.CaptchaRespData;
import com.petting.app.net.pojo.response.NetBaseResp;

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
        });
        return view;
    }
}
