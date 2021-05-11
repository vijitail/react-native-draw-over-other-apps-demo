package com.drawoverotherappsdemo;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class OverlayShowingService extends Service implements  OnClickListener {

    private WindowManager wm;

    private Button scheduleButton;

    private Button cancelButton;

    private LinearLayout view;

    public static final String ServiceIntent = "custom.OVERLAY_SERVICE";


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, PixelFormat.TRANSLUCENT);


        LayoutInflater li = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        view = (LinearLayout) li.inflate(R.layout.demo, null);

        scheduleButton = view.findViewById(R.id.schedule);

        cancelButton = view.findViewById(R.id.cancel);

        scheduleButton.setOnClickListener(this);

        cancelButton.setOnClickListener(this);

        wm.addView(view, params);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(view != null) {
            wm.removeView(view);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                this.stopSelf();
                break;
            case R.id.schedule:
                MainApplication application = (MainApplication) getApplication();
                ReactNativeHost reactNativeHost = application.getReactNativeHost();
                ReactInstanceManager reactInstanceManager = reactNativeHost.getReactInstanceManager();
                ReactContext reactContext = reactInstanceManager.getCurrentReactContext();
                if (reactContext != null) {
                    reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit("schedule", null);
                }
                this.stopSelf();
                break;
        }
    }

}
