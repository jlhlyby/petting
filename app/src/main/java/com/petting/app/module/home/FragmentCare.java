package com.petting.app.module.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.petting.app.R;
import com.petting.app.activity.ImagePagerActivity;
import com.petting.app.base.BaseFragment;
import com.petting.app.tools.HoldView;
import com.petting.app.tools.Tools;
import com.petting.app.view.CircularImage;

import java.util.ArrayList;

/**
 * Created by JiangXiongPing on 2017/11/14.
 * HOME-关注
 */

public class FragmentCare extends BaseFragment implements AbsListView.OnScrollListener {
    private PullToRefreshListView listview;
    private Thread mThreadGetInfo;//获取数据线程

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_care, null);
        initView(view);
        initClick();
        return view;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
// 当滑动到当前数据列表底部时，进行数据加载
        if (firstVisibleItem + visibleItemCount == totalItemCount) {
//            pro.setVisibility(View.VISIBLE);
            if (mThreadGetInfo == null || !mThreadGetInfo.isAlive()) {

                // 数据加载
                mThreadGetInfo = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        // 数据加载
//                        addLastList();
                        try {
                            Thread.sleep(1000);
//                            handle.sendEmptyMessage(1);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                    }
                });
                mThreadGetInfo.start();
            }
        }
    }

    @Override
    protected void initView(View root) {
        listview = (PullToRefreshListView) root.findViewById(R.id.fc_list);
        listview.setAdapter(new CationListAdapter());
    }

    @Override
    protected void initClick() {
        listview.setOnScrollListener(this);
    }

    class CationListAdapter extends BaseAdapter {

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

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            HoldView holdView = null;
            if (convertView == null) {
                holdView = new HoldView();
                convertView = View.inflate(getActivity(), R.layout.fragment_care_item, null);
                //动物头像
                holdView.mCircularImage1 = (CircularImage) convertView.findViewById(R.id.fci_icon);
                //动物昵称
                holdView.mTextView1 = (TextView) convertView.findViewById(R.id.fci_name);
                //动物性别
                holdView.mImageView2 = (ImageView) convertView.findViewById(R.id.fci_sex);
                //动物类别
                holdView.mTextView2 = (TextView) convertView.findViewById(R.id.fci_type);
                //发布时间
                holdView.mTextView3 = (TextView) convertView.findViewById(R.id.fci_time);
                //大图片
                holdView.mImageView1 = (ImageView) convertView.findViewById(R.id.fci_pic);
                //图片数量
                holdView.mTextView4 = (TextView) convertView.findViewById(R.id.fci_pic_num);
                //分享感言
                holdView.mTextView5 = (TextView) convertView.findViewById(R.id.fci_con);
                //用户小头像
                holdView.mCircularImage2 = (CircularImage) convertView.findViewById(R.id.fci_icon_app);
                //用户昵称
                holdView.mTextView6 = (TextView) convertView.findViewById(R.id.fci_app_name);
                //定位地址
                holdView.mTextView7 = (TextView) convertView.findViewById(R.id.fci_addr);
                //赞的数量
                holdView.mTextView8 = (TextView) convertView.findViewById(R.id.fci_zan_num);
                //评论的数量
                holdView.mTextView9 = (TextView) convertView.findViewById(R.id.fci_comment_num);
                //分享
                holdView.mImageView2 = (ImageView) convertView.findViewById(R.id.fci_share);
                //评论区
                holdView.mListView1 = (ListView) convertView.findViewById(R.id.fci_list);
                //唤起评论输入框
                holdView.mTextView10 = (TextView) convertView.findViewById(R.id.fci_comment_text);
                convertView.setTag(holdView);
            } else {
                holdView = (HoldView) convertView.getTag();
            }
            //浏览图片
            holdView.mImageView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<String> urls = new ArrayList<String>();
                    urls.add("https://www.baidu.com/img/bd_logo1.png");
                    urls.add("http://c.hiphotos.baidu.com/image/pic/item/3c6d55fbb2fb431697787bb029a4462308f7d391.jpg");
                    urls.add("http://b.hiphotos.baidu.com/image/pic/item/f9198618367adab49bfa1a8982d4b31c8601e4d6.jpg");
                    imageBrower(0, urls);
                }
            });
            holdView.mTextView10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Tools.showInputDial(getActivity(), "");
                }
            });
            // startActivity(new Intent(getActivity(), CareDetailActivity.class));
            return convertView;
        }
    }

    /**
     * 打开图片查看器
     *
     * @param position
     * @param urls2
     */
    protected void imageBrower(int position, ArrayList<String> urls2) {
        Intent intent = new Intent(getActivity(), ImagePagerActivity.class);
        // 图片url,为了演示这里使用常量，一般从数据库中或网络中获取
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, urls2);
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, position);
        getActivity().startActivity(intent);
    }
}
