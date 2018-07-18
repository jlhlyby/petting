package com.petting.app.module.home.share;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.petting.app.R;
import com.petting.app.base.BaseFragment;
import com.petting.app.view.HorizontalListView;

/**
 * Created by JiangXiongPing on 2017/11/14.
 * HOME-推荐
 */

public class FragmentShare extends BaseFragment implements AbsListView.OnScrollListener{
    private PullToRefreshListView listViewUp;//上部列表

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_share, null);
            initView(view);
        }
        return view;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    @Override
    protected void initView(View root) {
        listViewUp = (PullToRefreshListView) view.findViewById(R.id.fs_list);
        listViewUp.setAdapter(new ShareTipAdapter());
    }

    @Override
    protected void initClick() {

    }

    class ShareTipAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @TargetApi(Build.VERSION_CODES.M)
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if ((position + 1) % 8 != 0)
                convertView = View.inflate(getActivity(), R.layout.fragment_share_item, null);
            else {
                convertView = View.inflate(getActivity(), R.layout.fragment_share_tuijian_item, null);
                HorizontalListView hListView = (HorizontalListView) convertView.findViewById(R.id.fsti_bom_list);
                hListView.setAdapter(new TuijianAdapter());
                TuijianAdapter mAdapter = new TuijianAdapter();
                int totalHeight = 0;
                ViewGroup.LayoutParams params = hListView.getLayoutParams();
                for (int i = 0; i < mAdapter.getCount(); i++) {
                    View itemView = mAdapter.getView(i, null, hListView);
                    itemView.measure(0, 0);
                    if (mAdapter.getCount() % 2 != 0) {
                        totalHeight += itemView.getMeasuredWidth() * (mAdapter.getCount() + 1) / 2;
                    } else {
                        totalHeight += itemView.getMeasuredWidth() * (mAdapter.getCount()) / 2;
                    }
                    params.height = itemView.getMeasuredHeight();
                }
                params.width = totalHeight + 30;
                hListView.setLayoutParams(params);

            }
            return convertView;
        }
    }


    class TuijianAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(getActivity(), R.layout.fragment_share_card_item, null);
            return convertView;
        }
    }

}
