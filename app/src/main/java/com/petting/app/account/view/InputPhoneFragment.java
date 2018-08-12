package com.petting.app.account.view;


import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.petting.app.R;
import com.petting.app.account.view.base.AbsBaseLoginFragment;
import com.petting.app.net.NetCallBack;
import com.petting.app.net.NetHelper;
import com.petting.app.net.pojo.response.CaptchaRespData;
import com.petting.app.net.pojo.response.NetBaseResp;
import com.petting.app.tools.Contents;
import com.petting.app.tools.Tools;
import com.petting.app.tools.simplifycode.PetTextWatcher;

/**
 * 输入手机号页面
 */
public class InputPhoneFragment extends AbsBaseLoginFragment {
    private EditText phoneEt;
    private TextView nextTv;
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_input_phone;
    }

    @Override
    protected void initView(View root) {
        phoneEt = (EditText) root.findViewById(R.id.et_phone);
        nextTv = (TextView) root.findViewById(R.id.tv_next);
    }

    @Override
    public void onResume() {
        super.onResume();
        nextTv.setEnabled(Tools.isPhone(phoneEt.getText().toString()));
    }

    @Override
    protected void initClick() {
        phoneEt.addTextChangedListener(new PetTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                nextTv.setEnabled(Tools.isPhone(s.toString()));
            }
        });
        nextTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetHelper.getCode(new NetCallBack<NetBaseResp<CaptchaRespData>>(getContext()) {
                    @Override
                    public boolean onResponse(NetBaseResp<CaptchaRespData> resp) {
                        switch (resp.status){
                            case Contents.NET_STATUS_OK:
                                messenger.setSessionId(resp.data.getSessionId());
                                transform(InputPhoneFragment.this,new VerifyCodeFragment());
                                return true;
                        }
                        return false;
                    }

                });
            }
        });
    }

}
