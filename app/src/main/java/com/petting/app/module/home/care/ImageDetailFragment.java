package com.petting.app.module.home.care;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.lidroid.xutils.BitmapUtils;
import com.petting.app.R;
import com.petting.app.view.photoview.PhotoViewAttacher;

/**
 * Created by JiangXiongPing on 2017/11/17.
 */

public class ImageDetailFragment extends Fragment {
    private String mImageUrl;
    private ImageView mImageView;
    private ProgressBar progressBar;
    private PhotoViewAttacher mAttacher;
    private BitmapUtils mBitmapUtils;

    public static ImageDetailFragment newInstance(String imageUrl) {
        final ImageDetailFragment f = new ImageDetailFragment();

        final Bundle args = new Bundle();
        args.putString("url", imageUrl);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBitmapUtils = new BitmapUtils(getActivity());
        mImageUrl = getArguments() != null ? getArguments().getString("url")
                : null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.image_detail_fragment,
                container, false);
        mImageView = (ImageView) v.findViewById(R.id.image);
        mAttacher = new PhotoViewAttacher(mImageView);

        mAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {

            @Override
            public void onPhotoTap(View arg0, float arg1, float arg2) {
                getActivity().finish();
            }
        });

        progressBar = (ProgressBar) v.findViewById(R.id.loading);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mBitmapUtils.display(mImageView, mImageUrl);


//        ImageLoader.getInstance().displayImage(mImageUrl, mImageView,
//                new SimpleImageLoadingListener() {
//                    @Override
//                    public void onLoadingStarted(String imageUri, View view) {
//                        progressBar.setVisibility(View.VISIBLE);
//                    }
//
//                    @Override
//                    public void onLoadingFailed(String imageUri, View view,
//                                                FailReason failReason) {
//                        String message = null;
//                        switch (failReason.getType()) {
//                            case IO_ERROR:
//                                message = "下载错误";
//                                break;
//                            case DECODING_ERROR:
//                                message = "图片无法显示";
//                                break;
//                            case NETWORK_DENIED:
//                                message = "网络有问题，无法下载";
//                                break;
//                            case OUT_OF_MEMORY:
//                                message = "图片太大无法显示";
//                                break;
//                            case UNKNOWN:
//                                message = "未知的错误";
//                                break;
//                        }
//                        Toast.makeText(getActivity(), message,
//                                Toast.LENGTH_SHORT).show();
//                        progressBar.setVisibility(View.GONE);
//                    }
//
//                    @Override
//                    public void onLoadingComplete(String imageUri, View view,
//                                                  Bitmap loadedImage) {
//                        progressBar.setVisibility(View.GONE);
//                        mAttacher.update();
//                    }
//                });
    }
}
