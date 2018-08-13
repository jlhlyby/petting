package com.petting.app.tools;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Window;
import android.widget.EditText;

import com.petting.app.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static boolean isPhone(String str){
        return isNum(str) && str.length() == 11;

    }
    /**
     * 判断是否为数字
     * @param str
     * @return
     */
    public static boolean isNum(String str) {
        if(TextUtils.isEmpty(str)){
            return false;
        } else {
            Pattern pattern = Pattern.compile("[0-9]*");
            Matcher matcher = pattern.matcher(str);
            return matcher.matches();
        }
    }

    /**
     * 检查密码长度是否正确
     * @param password
     * @return
     */
    public static boolean isValidPwd(String password) {
        if (TextUtils.isEmpty(password)) {
            return false;
        }
        int min = 8;
        int max = 16;
        if (password.length() < min || password.length() > max) {
            return false;
        }
        return true;
    }
    /**
     * 密码中须包含“数字、字母、符号”中至少2种元素
     * @param password
     * @return
     */
    public static boolean chackPasswordMix(String password){
        if (TextUtils.isEmpty(password)) {
            return false;
        }
        Pattern p = Pattern.compile("[0-9]*");
        Matcher m = p.matcher(password);
        if ( m.matches()){
            return false;
        }
        p = Pattern.compile("[a-zA-Z]*");
        m = p.matcher(password);
        if ( m.matches()){
            return false;
        }
        p = Pattern.compile("[-/:;()$&@\".,?!'\\[\\]\\{\\}#%^*+=_\\|~<>€£¥]*");
        m = p.matcher(password);
        if ( m.matches()){
            return false;
        }
        return true;
    }
}
