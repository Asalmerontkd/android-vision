package io.mariachi.allianzvision.ui;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import io.mariachi.allianzvision.R;
import io.mariachi.allianzvision.camera.view.CameraView;

public class INE extends AppCompatActivity {

    private static final int REQUEST_CAMERA_PERMISSION = 1;

    private CameraView mCameraView;

    private Handler mBackgroundHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ine);



        settupCamera();
        //settupViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestPermissions();
        mCameraView.start();
    }

    @Override
    protected void onPause() {
        mCameraView.stop();
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBackgroundHandler != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                mBackgroundHandler.getLooper().quitSafely();
            } else {
                mBackgroundHandler.getLooper().quit();
            }
            mBackgroundHandler = null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA_PERMISSION:
                if (permissions.length != 1 || grantResults.length != 1) {
//                    throw new RuntimeException("Error on requesting camera permission.");
                    //TODO Error on requesting camera permission
                }
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    //TODO quit this message
                    Toast.makeText(this, "Permiso no aprobado",
                            Toast.LENGTH_SHORT).show();
                }
                // No need to start camera here; it is handled by onResume
                break;
        }
    }

    private void requestPermissions() {
        //TODO request permission
    }

    /*private void settupViews() {
        FloatingActionButton takePicture = (FloatingActionButton) findViewById(R.id.take_picture);
        if (takePicture != null) {
            takePicture.setOnClickListener(mOnClickListener);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }*/

    private void settupCamera() {
        mCameraView = (CameraView) findViewById(R.id.camera);
        if (mCameraView != null) {
            mCameraView.addCallback(mCallback);
        }
    }


    private Handler getBackgroundHandler() {
        if (mBackgroundHandler == null) {
            HandlerThread thread = new HandlerThread("background");
            thread.start();
            mBackgroundHandler = new Handler(thread.getLooper());
        }
        return mBackgroundHandler;
    }


    /**
     * Callback
     */

    private CameraView.Callback mCallback
            = new CameraView.Callback() {

        @Override
        public void onCameraOpened(CameraView cameraView) {
            log("onCameraOpened");
        }

        @Override
        public void onCameraClosed(CameraView cameraView) {
            log("onCameraClosed");
        }

        @Override
        public void onPictureTaken(CameraView cameraView, final byte[] data) {
            log("onPictureTaken " + data.length);
            Toast.makeText(cameraView.getContext(), "Foto tomada", Toast.LENGTH_SHORT)
                    .show();
            getBackgroundHandler().post(new Runnable() {
                @Override
                public void run() {
//                    File file = new File(
//                            MainActivity.this.getFilesDir().getPath() + "/pictures/",
//                            "picture.jpg");
//                    if (!file.exists()) {
//                        log("Directory not created");
//                        file.mkdir();
//                    }

                    File path = Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_PICTURES);
                    File file = new File(path, "DemoPicture" + new Random().nextInt(200) + ".jpg");

//                    File file=new File(Environment.getExternalStorageDirectory(), "photo.jpg");

                    // Make sure the Pictures directory exists.
                    path.mkdirs();

                    OutputStream os = null;
                    try {

                        os = new FileOutputStream(file);
                        os.write(data);
                        os.close();
                        log("picture succesfully " + file.getAbsolutePath());
                    } catch (IOException e) {
                        log("Cannot write to " + file.toString());
                        e.printStackTrace();
                    } finally {
                        if (os != null) {
                            try {
                                os.close();
                            } catch (IOException e) {
                                // Ignore
                            }
                        }
                    }
                }
            });
        }

        @Override
        public void onPreviewFrame(byte[] data) {
            log("preview: " + data.toString());
            super.onPreviewFrame(data);

        }
    };

    private void log(String content) {
        Log.e("myLog", content);
    }

}
