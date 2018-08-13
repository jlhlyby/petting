package com.petting.app.account.view;


import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.petting.app.R;
import com.petting.app.account.view.base.AbsBaseLoginFragment;
import com.petting.app.net.NetCallBack;
import com.petting.app.net.NetHelper;
import com.petting.app.net.pojo.request.PassReq;
import com.petting.app.net.pojo.response.NetBaseResp;
import com.petting.app.tools.Tools;
import com.petting.app.tools.simplifycode.PetTextWatcher;

/**
 * 设置密码
 */
public class SetPwdFragment extends AbsBaseLoginFragment {
    private EditText pwdEt;
    private TextView nextTv;
    private ImageView validIv;
    private ImageView mixIv;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_set_pwd;
    }

    @Override
    protected void initView(View root) {
        pwdEt = (EditText) root.findViewById(R.id.et_pwd);
        nextTv = (TextView) root.findViewById(R.id.tv_next);
        validIv = (ImageView) root.findViewById(R.id.iv_valid);
        mixIv = (ImageView) root.findViewById(R.id.iv_mix);
        validIv.setVisibility(View.INVISIBLE);
        mixIv.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void initClick() {
        pwdEt.addTextChangedListener(new PetTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                //todo 更新下一步,更新提示信息
                nextTv.setEnabled(checkPwd(s.toString()));
            }
        });
        nextTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPwd(pwdEt.getText().toString());
            }
        });

    }

    private void setPwd(String pwd) {
        if (TextUtils.isEmpty(pwd)){
            return;
        }
        NetHelper.pass(new PassReq()
                .setPassword(pwd)
                .setPhone(messenger.getPhone())
                .setSessionId(messenger.getSessionId())
                , new NetCallBack<NetBaseResp<PassReq>>(getContext()) {
            @Override
            public boolean onResponse(NetBaseResp<PassReq> resp) {
                //todo 处理请求
                return false;
            }
        });
    }
    private boolean checkPwd(String pwd){
        boolean isValid =false;  //判断密码长度限制
        boolean isMix = false;     //判断密码是否混合两种字符类型
        if (Tools.isValidPwd(pwd)){
            isValid =true;
            validIv.setVisibility(View.VISIBLE);
        }else{
            validIv.setVisibility(View.INVISIBLE);
        }
        if (Tools.chackPasswordMix(pwd)){
            isMix = true;
            mixIv.setVisibility(View.VISIBLE);
        }else{
            mixIv.setVisibility(View.INVISIBLE);
        }
        return isMix & isValid;

    }

}
