package com.petting.app.tools;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by yuboyang on 18/7/24.
 */

public class DisplayMetrics {
    private static final String TAG = "LoginDisplayMetrics";
    private static int width;
    private static float density;

    public static int getWidth(Context context) {
        if (width <= 0) {
            Resources resources = context.getResources();
            android.util.DisplayMetrics dm = resources.getDisplayMetrics();
            Logger.i(TAG ," getWidthPixels:" + dm.widthPixels);
            return dm.widthPixels;
        }
        Logger.i(TAG , " getWidth:" + width);
        return width;
    }

    public static void setWidth(int width) {
        DisplayMetrics.width = width;
        Logger.i(TAG , " setWidth:" + width);
    }

    public static float getDensity(Context context) {
        if (density <= 0) {
            Resources resources = context.getResources();
            android.util.DisplayMetrics dm = resources.getDisplayMetrics();
            density = dm.density;
        }
        Logger.i(TAG , " getDensity:" + density);
        return density;
    }
}
