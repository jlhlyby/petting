package com.petting.app.tools;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.petting.app.R;

/**
 * Created by HUASHAN on 2017/11/25.
 */
public class Tools {
    /**
     * 回复输入框
     */
    public static void showInputDial(Context context, String str) {
        Dialog dialog = new Dialog(context, R.style.MyDialogTheme);
        dialog.setContentView(R.layout.dial_input);
        EditText edt= (EditText) dialog.findViewById(R.id.di_edt);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        dialog.show();
    }
}
