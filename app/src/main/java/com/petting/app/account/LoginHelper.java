package com.petting.app.account;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.petting.app.account.view.LoginActivity;

/**
 * Created by yuboyang on 18/7/19.
 */

public class LoginHelper {
    public static void startLogin(Context context){
        startActivity(context, LoginActivity.class);

    }
    private static void startActivity(Context context ,Class<?> cls){
        Intent intent =  new Intent();
        if (!(context instanceof Activity)){
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent.setClass(context,cls));
    }
}
