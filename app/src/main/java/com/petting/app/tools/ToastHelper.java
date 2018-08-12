package com.petting.app.tools;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by yuboyang on 18/7/24.
 */

public class ToastHelper {
    public static void shortShow(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
    public static void longShow(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }
}
