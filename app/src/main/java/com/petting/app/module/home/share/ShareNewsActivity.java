package com.petting.app.module.home.share;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;

import com.petting.app.R;
import com.petting.app.databinding.ActivityShareNewsBinding;
import com.petting.app.model.ShareNewsModel;
import com.petting.app.tools.BaseActivity;
import com.petting.app.tools.Contents;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by JiangXiongPing on 2017/11/14.
 * 主页中间--分享
 */

public class ShareNewsActivity extends BaseActivity {
    SurfaceView surfaceView;//摄像区域
    private Camera camera;
    private Camera.Parameters parameters = null;
    private Bundle bundle = null;// 声明一个Bundle对象，用来存储数据
    ActivityShareNewsBinding binding;
    ShareNewsModel shareNewsModel;
    int windowWidth, windowHight;
    private int cameraPosition = 1;//0代表前置摄像头，1代表后置摄像头
    private SurfaceHolder holder;
    private Camera.Size cameraSize;
    private File mPicFile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_share_news);
        shareNewsModel = new ShareNewsModel(true, true);
        binding.setMode(shareNewsModel);
        binding.asnClose.setOnClickListener(this);
        binding.asnCameraRet.setOnClickListener(this);
        binding.asnCameraClk.setOnClickListener(this);
        binding.asnXc.setOnClickListener(this);
        binding.asnPz.setOnClickListener(this);
        binding.asnSurfaceView.setOnClickListener(this);
        binding.asnCloseEdit.setOnClickListener(this);
        //
        WindowManager mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        windowWidth = mWindowManager.getDefaultDisplay().getWidth();
        windowHight = mWindowManager.getDefaultDisplay().getHeight();
        holder = binding.asnSurfaceView.getHolder();//获得句柄
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
//        holder.setFixedSize(windowWidth, windowHight); // 设置Surface分辨率
        holder.setKeepScreenOn(true);// 屏幕常亮
        holder.addCallback(new SurfaceCallback());// 为SurfaceView的句柄添加一个回调函数
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.asn_surfaceView:
                doAutoFocus();
                break;
            case R.id.asn_close_edit:
                finish();
                break;
            case R.id.asn_close:
                finish();
                break;
            case R.id.asn_camera_ret://反转摄像头
                returnCamera();
                break;
            case R.id.asn_camera_clk:// 拍照
                if (camera != null) {
                    parameters = camera.getParameters(); // 获取各项参数
                    parameters.setPictureFormat(PixelFormat.JPEG); // 设置图片格式
                    parameters.setJpegQuality(80); // 设置照片质量
                    CamcorderProfile profile;
                    if (cameraPosition == 0) {
                        profile = (CamcorderProfile
                                .get(Camera.CameraInfo.CAMERA_FACING_FRONT, CamcorderProfile.QUALITY_HIGH));
                    } else {

                        profile = (CamcorderProfile
                                .get(Camera.CameraInfo.CAMERA_FACING_BACK, CamcorderProfile.QUALITY_HIGH));
                    }
//                    parameters.setPreviewSize( profile.videoFrameWidth, profile.videoFrameHeight); // 设置预览大小


                    parameters.setPictureSize(profile.videoFrameWidth, profile.videoFrameHeight);
                    Log.e("xxx", profile.videoFrameWidth + "  pr  " + profile.videoFrameHeight);
                    camera.setParameters(parameters);
                    camera.takePicture(null, null, new MyPictureCallback());
                }
                break;
            case R.id.asn_xc://相册模式
                shareNewsModel.setCameraMode(false);
                break;
            case R.id.asn_pz://拍照模式
                shareNewsModel.setCameraMode(true);
                break;
        }
    }

    private Camera.Size getSize(List<Camera.Size> list) {
        if (list.size() == 0) return null;
        double rate = Math.abs(list.get(0).height * 1.00 / list.get(0).width - 1);
        Camera.Size size = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            Camera.Size nowSize = list.get(i);
            if (Math.abs(nowSize.height * 1.00 / nowSize.width - 1) < rate) {
                size = nowSize;
            }
        }
        return size;
    }


    /**
     * 翻转摄像头
     */
    private void returnCamera() {
        //切换前后摄像头
        int cameraCount = 0;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        cameraCount = Camera.getNumberOfCameras();//得到摄像头的个数

        for (int i = 0; i < cameraCount; i++) {
            Camera.getCameraInfo(i, cameraInfo);//得到每一个摄像头的信息
            if (cameraPosition == 1) {
                //现在是后置，变更为前置
                if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {//代表摄像头的方位，CAMERA_FACING_FRONT前置      CAMERA_FACING_BACK后置
                    camera.stopPreview();//停掉原来摄像头的预览
                    camera.release();//释放资源
                    camera = null;//取消原来摄像头
//                    camera = Camera.open(i);//打开当前选中的摄像头
//                    try {
//                        camera.setDisplayOrientation(/*getPreviewDegree(ShareNewsActivity.this)*/90);
//                        camera.setPreviewDisplay(holder);//通过surfaceview显示取景画面
//                    } catch (IOException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//                    camera.startPreview();//开始预览
                    initCamera(i);
                    cameraPosition = 0;
                    break;
                }
            } else {
                //现在是前置， 变更为后置
                if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {//代表摄像头的方位，CAMERA_FACING_FRONT前置      CAMERA_FACING_BACK后置
                    camera.stopPreview();//停掉原来摄像头的预览
                    camera.release();//释放资源
                    camera = null;//取消原来摄像头
//                    camera = Camera.open(i);//打开当前选中的摄像头
//                    try {
//                        camera.setDisplayOrientation(/*getPreviewDegree(ShareNewsActivity.this)*/90);
//                        camera.setPreviewDisplay(holder);//通过surfaceview显示取景画面
//                    } catch (IOException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//                    camera.startPreview();//开始预览
                    initCamera(i);
                    cameraPosition = 1;
                    break;
                }
            }

        }
    }

    // handle button auto focus
    private void doAutoFocus() {
        parameters = camera.getParameters();
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        camera.setParameters(parameters);
        camera.autoFocus(new Camera.AutoFocusCallback() {
            @Override
            public void onAutoFocus(boolean success, Camera camera) {
                if (success) {
                    camera.cancelAutoFocus();// 只有加上了这一句，才会自动对焦。
                    if (!Build.MODEL.equals("KORIDY H30")) {
                        parameters = camera.getParameters();
                        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);// 1连续对焦
                        camera.setParameters(parameters);
                    } else {
                        parameters = camera.getParameters();
                        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
                        camera.setParameters(parameters);
                    }
                }
            }
        });
    }

    /**
     * 重构相机照相回调类
     *
     * @author pc
     */
    private final class SurfaceCallback implements SurfaceHolder.Callback {

        @SuppressWarnings("deprecation")
        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {
            // TODO Auto-generated method stub

        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            // TODO Auto-generated method stub
            initCamera(-1);
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            // TODO Auto-generated method stub
            if (camera != null) {
                camera.release(); // 释放照相机
                camera = null;
            }
        }
    }

    private void initCamera(int num) {
        try {
            if (camera != null) {
                camera.release();
                camera = null;
            }
            if (num == -1)
                camera = Camera.open(); // 打开摄像头
            else
                camera = Camera.open(num);
            camera.setDisplayOrientation(/*getPreviewDegree(ShareNewsActivity.this)*/90);
            camera.setPreviewDisplay(holder); // 设置用于显示拍照影像的SurfaceHolder对象
            // 实现自动对焦
            camera.autoFocus(new Camera.AutoFocusCallback() {
                @Override
                public void onAutoFocus(boolean success, Camera camera) {
                    if (success) {
                        camera.cancelAutoFocus();// 只有加上了这一句，才会自动对焦
                        doAutoFocus();
                    }
                }
            });
            //由于相机界面调整，需要以下设置
            parameters = camera.getParameters();
            //设置PreviewSize
            List<Camera.Size> rawSupportedSizes = parameters.getSupportedPreviewSizes();
            cameraSize = getSize(rawSupportedSizes);
            parameters.setPreviewSize(cameraSize.width, cameraSize.height);
            camera.setParameters(parameters);
            camera.startPreview(); // 开始预览
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 重构照相类
     *
     * @author
     */
    private final class MyPictureCallback implements Camera.PictureCallback {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            try {
                bundle = new Bundle();
                bundle.putByteArray("bytes", data); //将图片字节数据保存在bundle当中，实现数据交换
                saveToSDCard(data); // 保存图片到sd卡中
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将拍下来的照片存放在SD卡中
     *
     * @param data
     * @throws IOException
     */
    public void saveToSDCard(byte[] data) throws IOException {
        //剪切为正方形
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//        int wid = b.getWidth();
//        int hei = b.getHeight();
//        Log.e("xxx", wid + "   " + hei);
//        if (hei < wid) wid = hei;
//        Log.e("xxx", wid + "   " + hei);
//        Bitmap bitmap = Bitmap.createBitmap(b, 0, 0, wid, wid);
        //生成文件
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss"); // 格式化时间
        String filename = format.format(date) + ".jpg";
        File fileFolder = new File(Contents.CAMERA_PATH);
        if (!fileFolder.exists()) { // 如果目录不存在，则创建一个名为"finger"的目录
            fileFolder.mkdirs();
        }
        mPicFile = new File(fileFolder, filename);
        Log.e("xxx", mPicFile.getAbsolutePath().toString());
        FileOutputStream outputStream = new FileOutputStream(mPicFile); // 文件输出流
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        outputStream.flush();
        outputStream.write(data); // 写入sd卡中
        outputStream.close(); // 关闭输出流
//        Intent intent = new Intent();
//        intent.putExtra("path", Contents.CAMERA_PATH + filename);
//        setResult(1, intent);
        if (camera != null) {
            camera.stopPreview();
            camera.release(); // 释放照相机
            camera = null;
        }
        shareNewsModel.setToShowSurfaceView(false);
        //将图片设置到界面
        bitmap = rotaingImageView(90, bitmap);
        binding.asnCameraPicShow.setImageBitmap(bitmap);
    }

    /**
     * 旋转图片
     *
     * @param angle  被旋转角度
     * @param bitmap 图片对象
     * @return 旋转后的图片
     */
    public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
        Bitmap returnBm = null;
        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            returnBm = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
        }
        if (returnBm == null) {
            returnBm = bitmap;
        }
        if (bitmap != returnBm) {
            bitmap.recycle();
        }
        return returnBm;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (camera != null) {
            camera.stopPreview();
            camera.release(); // 释放照相机
            camera = null;
        }
        if (mPicFile != null && mPicFile.exists()) mPicFile.delete();
    }
}
