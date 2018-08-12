package com.petting.app.net;

import android.content.Context;
import android.text.TextUtils;

import com.petting.app.R;
import com.petting.app.net.pojo.response.NetBaseResp;
import com.petting.app.tools.ToastHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yuboyang on 18/7/24.
 */

public abstract class NetCallBack<T extends NetBaseResp> implements Callback {
    Context context;

    public NetCallBack(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(Call call, Response response) {
        try {
            T resp = (T) response.body();
            if (resp != null) {
                if (!onResponse(resp)) {
                    ToastHelper.shortShow(context, TextUtils.isEmpty(resp.message) ?
                            context.getString(R.string.net_error) : resp.message);
                }
            } else {
                ToastHelper.shortShow(context, context.getString(R.string.net_error));
            }

        } catch (Exception e) {
            e.printStackTrace();
            ToastHelper.shortShow(context, context.getString(R.string.net_error));
        }
    }


    @Override
    public void onFailure(Call call, Throwable t) {
        t.printStackTrace();
        ToastHelper.shortShow(context, context.getString(R.string.net_error));
    }

    public abstract boolean onResponse(T resp);

}
