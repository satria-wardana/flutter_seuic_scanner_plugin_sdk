package com.seuic.scanner.flutter_seuic_scanner_plugin_sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.google.gson.Gson;
import com.seuic.scanner.flutter_seuic_scanner_plugin_sdk.model.BarcodeScanner;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.JSONMethodCodec;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugin.common.StandardMethodCodec;

public class ScannerHandler implements EventChannel.StreamHandler{

    private BroadcastReceiver chargingStateChangeReceiver;


    private final Context applicationContext;

//    private ScanReceiver scanReceiver;


    private static String BAR_CODE = "barcode";

    private static String CODE_TYPE = "codetype";

    private static String LENGTH = "length";
    public ScannerHandler(Context context){
        this.applicationContext = context;
    }

    /**
     * @param arguments stream configuration arguments, possibly null.
     * @param events    an {@link EventChannel.EventSink} for emitting events to the Flutter receiver.
     */
    @Override
    public void onListen(Object arguments, EventChannel.EventSink events) {
//        scanReceiver = new ScanReceiver();
        chargingStateChangeReceiver = scanReceiver(events);
        IntentFilter intentFilter  = new IntentFilter();
        intentFilter.addAction("seuic.android.scanner.scannertestreciever");
        intentFilter.setPriority(Integer.MAX_VALUE);
        applicationContext.registerReceiver(chargingStateChangeReceiver,intentFilter);
    }

    /**
     * @param arguments stream configuration arguments, possibly null.
     */
    @Override
    public void onCancel(Object arguments) {
        applicationContext.unregisterReceiver(chargingStateChangeReceiver);
        chargingStateChangeReceiver = null;
    }

    private BroadcastReceiver scanReceiver(final EventChannel.EventSink events){
        return new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle bundle = intent.getExtras();
                Gson gson = new Gson();

                BarcodeScanner barcodeScanner = new BarcodeScanner();
                barcodeScanner.setBarcode(bundle.getString(BAR_CODE));
                barcodeScanner.setCodeType(bundle.getString(CODE_TYPE));
                barcodeScanner.setLength(bundle.getInt(LENGTH));

                events.success(gson.toJson(barcodeScanner));

            }
        };
    }

//    public class ScanReceiver extends BroadcastReceiver {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            Bundle bundle = intent.getExtras();
//            System.out.println("barcode: "
//                    + bundle.getString(ScannerService.BAR_CODE) + "\ntype: "
//                    + bundle.getString(ScannerService.CODE_TYPE) + "\nlength: "
//                    + bundle.getInt(ScannerService.LENGTH) + "\n");
//        }
//    }

}
