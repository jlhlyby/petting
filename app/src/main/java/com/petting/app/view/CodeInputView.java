package com.petting.app.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.petting.app.R;
import com.petting.app.tools.DisplayMetrics;
import com.petting.app.tools.Logger;
import com.petting.app.tools.UiThreadHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuboyang on 18/7/24.
 */

public class CodeInputView extends LinearLayout {
    private static final String TAG = "CodeInputView";
    private static final int DEFAULT_CODE_LENGTH = 4;
    /**
     * 输入框的数量
     */
    private int editNum;
    /**
     * 所有输入框
     */
    private List<CodeInputEditText> codes;

    public CodeInputView(Context context) {
        super(context);
        initView(context, null);
    }


    public CodeInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
        initInputType(context, attrs);
    }

    public CodeInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
        initInputType(context, attrs);
    }

    private void initInputType(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CodeInputView);
        int inputType = ta.getInt(R.styleable.CodeInputView_android_inputType, -1);
        if (inputType > 0) {
            for (EditText editText : codes) {
                editText.setInputType(inputType);
            }
        }
        ta.recycle();
    }


    private void initView(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_code_input, this);
        LinearLayout ll_code_layout = (LinearLayout) view.findViewById(R.id.ll_code_layout);
        int width = (int) (DisplayMetrics.getWidth(context) - 40 * DisplayMetrics.getDensity(context));   //计算
        int boxw = width / 7;
        Logger.i("CodeInputView"," width:" + width);
        Logger.i("CodeInputView"," boxw :" + boxw);
        Logger.i("CodeInputView"," margin:" + boxw / 5);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyCoedEdit);
        editNum = ta.getInt(R.styleable.MyCoedEdit_CodeLength, DEFAULT_CODE_LENGTH);
        ta.recycle();
        codes = new ArrayList<>();
        for (int i = 0; i < editNum; i++) {
            View view1 = LayoutInflater.from(getContext()).inflate(R.layout.view_code_edit, null);
            CodeInputEditText smallEdit = (CodeInputEditText) view1.findViewById(R.id.v_input_edit);
            smallEdit.setContentDescription("s" + i);
            LinearLayout.LayoutParams params = (LayoutParams) smallEdit.getLayoutParams();
            params.height = boxw;
            params.width = boxw;
            params.weight = 1;
            if (i == editNum - 1) {
                params.setMargins(0, 0, 0, 0);
            } else {
                params.setMargins(0, 0, boxw / 5, 0);
            }
            smallEdit.setVisibility(View.VISIBLE);
            codes.add(smallEdit);
            ll_code_layout.addView(view1);


        }
        CodeFocusChangeListener codeFocusChangeListener = new CodeFocusChangeListener();
        for (CodeInputEditText editText : codes) {
            editText.setOnFocusChangeListener(codeFocusChangeListener);
            editText.addTextChangedListener(new CodeTextChangedListener(codes.indexOf(editText)));
            editText.setDelKeyEventListener(new CodeInputEditText.OnDelKeyEventListener() {
                @Override
                public void onDeleteClick(CodeInputEditText editText) {
                    if (TextUtils.isEmpty(editText.getText().toString())) {
                        moveToForward(editText);
                    }
                }
            });

        }
    }

    public void setCode(final String code) {
        Log.d(TAG, "[setCode] " + code);
        if (TextUtils.isEmpty(code) || code.length() != codes.size()) {
            return;
        }
        UiThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < codes.size() && i < code.length(); i++) {
                    codes.get(i).setText(code.substring(i,i+1));
                }
            }
        });

    }

    public String getCode() {
        final StringBuilder sb = new StringBuilder();
        for (EditText et : codes) {
            sb.append(et.getText().toString());
        }
        return sb.toString();
    }

    private boolean isCodeReady(EditText code) {
        return !TextUtils.isEmpty(code.getText().toString());
    }

    private void checkComplete() {
        for (EditText editText : codes) {
            if (!isCodeReady(editText)) {
                return;
            }
        }
        Log.i(TAG, "checkComplete: ");
        if (inputCompleteListener != null) {
            inputCompleteListener.onInputComplete(getCode());
        }
    }

    public void clearCode() {
        for (EditText editText : codes) {
            editText.setText("");
        }
        codes.get(0).requestFocus();
        if (clearCodeListener != null) {
            clearCodeListener.onClearCode();
        }
    }
    public EditText getCodeView(int index){
        if (codes == null){
            return null;
        }
        if (index >= codes.size()){
            return null;
        }
        return codes.get(0);
    }

    class CodeFocusChangeListener implements View.OnFocusChangeListener {

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            Log.d("tom", "onFocusChange viewId: " + v.getId() + " focus: " + hasFocus);
        }
    }

    class CodeTextChangedListener implements TextWatcher {

        int index = 0;

        public CodeTextChangedListener(int index) {
            this.index = index;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        /**
         * 控制输入内容和光标位置
         * 1.如果输入一个字符 将光标移动到下个输入框
         * 2.如果输入多个字符，根据光标位置保留一个字符
         * 光标在第一位则保留第一位字符，否则保留最后一位
         *
         * @param editable
         */
        @Override
        public void afterTextChanged(Editable editable) {
            Log.d(TAG, "afterTextChanged: " + editable.toString());
            if (index < 0 || index > codes.size() || TextUtils.isEmpty(editable.toString())) {
                return;
            }
            boolean isfirstSelect  = codes.get(index).getSelectionStart() == 1;
            if (editable.length() > 1) {
                editable.replace(0, editable.length(), editable.toString(),
                        isfirstSelect ? 0 : editable.length() - 1, isfirstSelect ? 1 : editable.length());
            } else {
                if (index + 1 < codes.size()) {
                    codes.get(index + 1).requestFocus();
                }
                checkComplete();
            }

        }
    }


    private InputCompleteListener inputCompleteListener;

    public void setInputCompleteListener(InputCompleteListener inputCompleteListener) {
        this.inputCompleteListener = inputCompleteListener;
    }

    public interface InputCompleteListener {
        void onInputComplete(String code);
    }


    private ClearCodeListener clearCodeListener;

    public void setClearCodeListener(ClearCodeListener clearCodeListener) {
        this.clearCodeListener = clearCodeListener;
    }

    public interface ClearCodeListener {
        void onClearCode();
    }


    private void moveToForward(EditText editText) {
        final int index = codes.indexOf(editText);
        if (index > 0) {
            codes.get(index - 1).setText("");
            codes.get(index - 1).requestFocus();
        }
    }

}
