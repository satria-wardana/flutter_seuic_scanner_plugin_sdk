package com.seuic.scanner.flutter_seuic_scanner_plugin_sdk;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.seuic.scanner.DecodeInfo;
import com.seuic.scanner.DecodeInfoCallBack;
import com.seuic.scanner.Scanner;
import com.seuic.scanner.ScannerFactory;
import com.seuic.scanner.ScannerKey;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ScannerService extends Service implements DecodeInfoCallBack {


    Scanner scanner;

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    /**
     *
     */
    @Override
    public void onCreate() {
        super.onCreate();
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //当sdk版本大于26
            String id = "MyService";
            String description = "my-service";
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel channel = new NotificationChannel(id, description, importance);
            manager.createNotificationChannel(channel);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "messages")
                    .setContentText("扫描服务正在后台运行")
                    .setContentTitle("ScannerService")
                    .setSmallIcon(R.mipmap.ic_launcher);

            manager.notify(1, builder.build());
            startForeground(101, builder.build());
        }


        scanner = ScannerFactory.getScanner(this);
        scanner.open();
        scanner.setDecodeInfoCallBack(this);



        executorService.execute(new ScannerTask(scanner));
    }



    public static final String BAR_CODE = "barcode";
    public static final String CODE_TYPE = "codetype";
    public static final String LENGTH = "length";
    public static final String ACTION = "seuic.android.scanner.scannertestreciever";


    private final int NOTIFICATION_ID_ICON = 1008611;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * @param intent The Intent that was used to bind to this service,
     *               as given to {@link android.content.Context#bindService
     *               Context.bindService}.  Note that any extras that were included with
     *               the Intent at that point will <em>not</em> be seen here.
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * @param decodeInfo
     */
    @Override
    public void onDecodeComplete(DecodeInfo decodeInfo) {
        Intent intent = new Intent(ACTION);
        Bundle bundle = new Bundle();
        bundle.putString(BAR_CODE, decodeInfo.barcode);
        bundle.putString(CODE_TYPE, decodeInfo.codetype);
        bundle.putInt(LENGTH, decodeInfo.length);
        intent.putExtras(bundle);
        sendBroadcast(intent);
    }

    @Override
    public void onDestroy() {
        scanner.setDecodeInfoCallBack(null);
        scanner.close();
        executorService.shutdownNow();
        deleteIconToStatusbar();
        super.onDestroy();
    }



    private void deleteIconToStatusbar()
    {
        NotificationManager nm = (NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);
        nm.cancel("direct_tag", NOTIFICATION_ID_ICON);
    }
}
