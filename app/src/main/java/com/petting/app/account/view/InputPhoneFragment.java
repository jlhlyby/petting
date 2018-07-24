package com.petting.app.account.view;


import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.petting.app.R;
import com.petting.app.account.view.base.AbsBaseLoginFragment;
import com.petting.app.net.NetHelper;
import com.petting.app.net.pojo.response.CaptchaRespData;
import com.petting.app.net.pojo.response.NetBaseResp;
import com.petting.app.tools.Tools;
import com.petting.app.tools.simplifycode.PetTextWatcher;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                NetHelper.getCode(new Callback<NetBaseResp<CaptchaRespData>>() {
                    @Override
                    public void onResponse(Call<NetBaseResp<CaptchaRespData>> call, Response<NetBaseResp<CaptchaRespData>> response) {
                        LogI("onResponse: "+response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<NetBaseResp<CaptchaRespData>> call, Throwable t) {
                        LogI("onFailure: "+t);
                    }
                });
            }
        });
    }

}
