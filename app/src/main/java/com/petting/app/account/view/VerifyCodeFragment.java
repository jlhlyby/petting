package com.petting.app.account.view;

import android.view.View;

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

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_verify_code;
    }

    @Override
    protected void initView(View root) {
        codeView = (CodeInputView) root.findViewById(R.id.v_code);
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
                                //todo跳转设置密码页面
                                showShortToast("验证成功");
                                return true;
                        }
                        return false;
                    }
                });
    }
}
