package com.petting.app.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.petting.app.R;
import com.petting.app.tools.Contents;

import java.io.Serializable;

/**
 * Created by yuboyang on 18/7/21.
 */

public class BaseFragmentActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_fragment);
    }

    public <T extends Serializable> void transform(Fragment fromFragment, Fragment toFragment, T messenger) {
        try {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.setCustomAnimations(R.anim.login_unify_anim_right_slide_in, R.anim.login_unify_anim_left_side_out, R.anim.login_unify_anim_left_side_int, 0);
            //todo 加动画效果
            Bundle bundle = new Bundle();
            if (messenger != null){
                bundle.putSerializable(Contents.KEY_FRAGMENT_MESSENGER,messenger);
            }
            toFragment.setArguments(bundle);
            transaction.replace(R.id.fl_fragment, toFragment, toFragment.getClass().getName());
            if (fromFragment != null){     //如果fromState == null,表示流程从新开始
                transaction.addToBackStack(null);
            }else{
                getSupportFragmentManager()
                        .popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE); //进入首页清除所有回退栈
            }
            transaction.commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
