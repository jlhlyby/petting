package com.petting.app.module.find;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.petting.app.R;

/**
 * Created by JiangXiongPing on 2017/11/14.
 */

public class FragmentFind extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, null);
        TextView tv = (TextView) view.findViewById(R.id.f_text);
        tv.setText("Find");
        Log.e("xxx","Find");
        return view;
    }
}
