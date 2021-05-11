package com.drawoverotherappsdemo;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.util.Map;
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;


public class DrawOverModule extends ReactContextBaseJavaModule {
    DrawOverModule(ReactApplicationContext context) {
        super(context);
    }


    @NonNull
    @Override
    public String getName() {
        return "DrawOverModule";
    }

    @ReactMethod
    public void displayDrawOver() {
        ReactApplicationContext  reactContext = getReactApplicationContext();
        Intent service = new Intent(reactContext, OverlayShowingService.class);
        reactContext.startService(service);
    }

}
