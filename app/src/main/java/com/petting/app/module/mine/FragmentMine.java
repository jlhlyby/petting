package com.petting.app.module.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.petting.app.R;
import com.petting.app.account.LoginHelper;

/**
 * Created by JiangXiongPing on 2017/11/14.
 */

public class FragmentMine extends Fragment {
    private static final String TAG = "FragmentMine";

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        view.findViewById(R.id.f_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginHelper.startLogin(getContext());
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
