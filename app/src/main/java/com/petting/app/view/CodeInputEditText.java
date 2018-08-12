package com.petting.app.view;

/**
 * Created by yuboyang on 18/7/24.
 */


import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;

/**
 * @see <a href="http://stackoverflow.com/questions/4886858/android-edittext-deletebackspace-key-event">Stack
 * Overflow</a>
 */
public class CodeInputEditText extends EditText {

    private OnDelKeyEventListener delKeyEventListener;

    public CodeInputEditText(Context context) {
        super(context);
    }

    public CodeInputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CodeInputEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        return new ZanyInputConnection(super.onCreateInputConnection(outAttrs), true,this);
    }

    private class ZanyInputConnection extends InputConnectionWrapper {
        CodeInputEditText editText;
        public ZanyInputConnection(InputConnection target, boolean mutable, CodeInputEditText editText) {
            super(target, mutable);
            this.editText = editText;
        }

        @Override
        public boolean sendKeyEvent(KeyEvent event) {
            if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_DEL) {
                if (delKeyEventListener != null) {
                    delKeyEventListener.onDeleteClick(editText);
                }
            }
            return super.sendKeyEvent(event);
        }

        @Override
        public boolean deleteSurroundingText(int beforeLength, int afterLength) {
            sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
            return super.deleteSurroundingText(beforeLength, afterLength);
        }
    }

    public void setDelKeyEventListener(OnDelKeyEventListener delKeyEventListener) {
        this.delKeyEventListener = delKeyEventListener;
    }

    public interface OnDelKeyEventListener {

        void onDeleteClick(CodeInputEditText editText);

    }

}
