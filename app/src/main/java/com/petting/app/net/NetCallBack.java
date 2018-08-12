package com.petting.app.net;

import android.content.Context;

import com.petting.app.R;
import com.petting.app.tools.ToastHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yuboyang on 18/7/24.
 */

public abstract class NetCallBack<T> implements Callback {
    Context context;

    public NetCallBack(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(Call call, Response response) {
        T resp = (T) response.body();
        try {
            if (resp != null && onResponse(resp)) {
                return;
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
