package com.petting.app.store;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by yuboyang on 18/7/15.
 */

public class BaseStore {
    private static Context sContext;
    private String spName;
    private SharedPreferences mSharedPreferences;

    public static void init(Context context) {
        sContext = context;
    }

    public BaseStore(String spName) {
        this.spName = spName;
        mSharedPreferences = sContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
    }

    protected SharedPreferences.Editor getEditor() {
        if (mSharedPreferences != null) {
            return mSharedPreferences.edit();
        }
        return null;
    }

    protected boolean putString(String key, String value) {
        SharedPreferences.Editor editor = getEditor();
        if (editor != null) {
            editor.putString(key, value);
            editor.apply();
            return true;
        }
        return false;
    }

    protected boolean putInt(String key, int value) {
        SharedPreferences.Editor editor = getEditor();
        if (editor != null) {
            editor.putInt(key, value);
            editor.apply();
            return true;
        }
        return false;
    }

    protected boolean putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = getEditor();
        if (editor != null) {
            editor.putBoolean(key, value);
            editor.apply();
            return true;
        }
        return false;
    }

    protected String getString(String key, String defValue) {
        if (mSharedPreferences != null) {
            return mSharedPreferences.getString(key, defValue);
        }
        return null;
    }
    protected int getInt(String key, int defValue){
        if (mSharedPreferences != null) {
            return mSharedPreferences.getInt(key, defValue);
        }
        return 0;
    }
    protected boolean getBoolean(String key, boolean defValue){
        if (mSharedPreferences != null) {
            return mSharedPreferences.getBoolean(key, defValue);
        }
        return false;
    }

}
