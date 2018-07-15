package com.petting.app.model;

import android.databinding.BaseObservable;

import com.petting.app.BR;

/**
 * Created by HUASHAN on 2017/12/11.
 */
public class ShareNewsModel extends BaseObservable {
    /**
     * 是拍照模式吗
     */
    private boolean isCameraMode;
    /**
     * 显示SurfaceView
     */
    private boolean toShowSurfaceView;

    public boolean isCameraMode() {
        return isCameraMode;
    }

    public ShareNewsModel(boolean toShowSurfaceView, boolean isCameraMode) {
        this.toShowSurfaceView = toShowSurfaceView;
        this.isCameraMode = isCameraMode;
    }

    public void setCameraMode(boolean cameraMode) {
        isCameraMode = cameraMode;
        notifyPropertyChanged(BR._all);
    }

    public boolean isToShowSurfaceView() {
        return toShowSurfaceView;
    }

    public void setToShowSurfaceView(boolean toShowSurfaceView) {
        this.toShowSurfaceView = toShowSurfaceView;
        notifyPropertyChanged(BR._all);
    }
}
