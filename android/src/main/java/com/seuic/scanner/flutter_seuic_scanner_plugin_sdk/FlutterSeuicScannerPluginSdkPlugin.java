package com.seuic.scanner.flutter_seuic_scanner_plugin_sdk;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;

import com.seuic.scanner.flutter_seuic_scanner_plugin_sdk.constant.Channel;
import com.seuic.scanner.flutter_seuic_scanner_plugin_sdk.constant.Method;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/**
 * FlutterSeuicScannerPluginSdkPlugin
 */
public class FlutterSeuicScannerPluginSdkPlugin implements FlutterPlugin, MethodCallHandler {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private MethodChannel channel;

    private EventChannel eventChannel;

    private Context applicationContext;

    private Intent scannerIntent;

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {

        applicationContext = flutterPluginBinding.getApplicationContext();
        //Android 8.0  26
        //一种 Notification 对应一个 NotificationChannel
        //在 Application 注册 channel 可以在 app 启动时就完成注册
        System.out.println("1111111");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("messages", "Messages", NotificationManager.IMPORTANCE_LOW);
            NotificationManager manager = applicationContext.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), Channel.CHANNEL);
        channel.setMethodCallHandler(this);

        eventChannel = new EventChannel(flutterPluginBinding.getBinaryMessenger(), "seuic.android.scanner.scannertestreciever");
        eventChannel.setStreamHandler(new ScannerHandler(flutterPluginBinding.getApplicationContext()));

    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        scannerIntent = new Intent(applicationContext, ScannerService.class);
        if (call.method.equals("getPlatformVersion")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                applicationContext.startForegroundService(scannerIntent);
            } else {
                applicationContext.startService(scannerIntent);
            }
            result.success("Android " + android.os.Build.VERSION.RELEASE);
        } else if (call.method.equals(Method.STOP_SCANNER_SERVICE)) {
            applicationContext.stopService(scannerIntent);

            result.success(Method.STOP_SCANNER_SERVICE);
        } else if (call.method.equals(Method.START_SCANNER_SERVICE)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                applicationContext.startForegroundService(scannerIntent);
            } else {
                applicationContext.startService(scannerIntent);
            }
            result.success(Method.START_SCANNER_SERVICE);
        } else {
            result.notImplemented();
        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        channel.setMethodCallHandler(null);
        eventChannel.setStreamHandler(null);
        applicationContext.stopService(scannerIntent);
    }
}
