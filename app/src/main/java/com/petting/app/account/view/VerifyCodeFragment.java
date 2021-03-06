package com.petting.app.account.view;

import android.view.View;
import android.widget.TextView;

import com.petting.app.R;
import com.petting.app.account.view.base.AbsBaseLoginFragment;
import com.petting.app.net.NetCallBack;
import com.petting.app.net.NetHelper;
import com.petting.app.net.pojo.request.CheckReq;
import com.petting.app.net.pojo.response.NetBaseResp;
import com.petting.app.tools.Contents;
import com.petting.app.view.CodeInputView;

/**
 * Created by yuboyang on 18/7/24.
 */

public class VerifyCodeFragment extends AbsBaseLoginFragment {
    CodeInputView codeView;
    TextView hintTv;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_verify_code;
    }

    @Override
    protected void initView(View root) {
        codeView = (CodeInputView) root.findViewById(R.id.v_code);
        hintTv = (TextView) root.findViewById(R.id.tv_code_hint);
        hintTv.setText(getString(R.string.login_verify_code_title,messenger.getPhone()));
    }

    @Override
    protected void initClick() {
        codeView.setInputCompleteListener(new CodeInputView.InputCompleteListener() {
            @Override
            public void onInputComplete(String code) {
                check(code);
            }
        });

    }

    private void check(String code) {
        NetHelper.check(new CheckReq().setCaptcha(code).setSessionId(messenger.getSessionId())
                , new NetCallBack<NetBaseResp<CheckReq>>(getContext()) {
                    @Override
                    public boolean onResponse(NetBaseResp<CheckReq> resp) {
                        switch (resp.status){
                            case Contents.NET_STATUS_OK:
                                transform(VerifyCodeFragment.this,new SetPwdFragment());
                                return true;
                            default:
                                codeView.clearCode();
                                return false;
                        }
                    }
                });
    }
}
