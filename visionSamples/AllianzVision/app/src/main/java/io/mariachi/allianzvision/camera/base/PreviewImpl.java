package io.mariachi.allianzvision.camera.base;

/**
 * Created by Ken on 08/03/17.
 */


import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.View;

/**
 * Encapsulates all the operations related to camera preview in a backward-compatible manner.
 */
public abstract class PreviewImpl {

    public interface Callback {
        void onSurfaceChanged();
    }


    private Callback mCallback;

    private int mWidth;

    private int mHeight;

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    public abstract Surface getSurface();

    public abstract View getView();

    public abstract Class getOutputClass();

    public abstract void setDisplayOrientation(int displayOrientation);

    public abstract boolean isReady();

    protected void dispatchSurfaceChanged() {
        mCallback.onSurfaceChanged();
    }



    public SurfaceHolder getSurfaceHolder() {
        return null;
    }

    public Object getSurfaceTexture() {
        return null;
    }

    public void setBufferSize(int width, int height) {
    }

    public void setSize(int width, int height) {
        mWidth = width;
        mHeight = height;
    }

    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }

    public abstract void setOnTouchEvent(View.OnTouchListener touchEvent);

}