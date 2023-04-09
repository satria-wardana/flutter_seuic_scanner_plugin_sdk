package com.seuic.scanner.flutter_seuic_scanner_plugin_sdk;


import com.seuic.scanner.Scanner;
import com.seuic.scanner.ScannerKey;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ScannerTask implements Runnable {

    public ScannerTask(Scanner scanner) {
        this.scanner = scanner;
    }


    Scanner scanner;

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {

        int ret1 = ScannerKey.open();
        if (ret1 > -1) {
            while (!Thread.currentThread().isInterrupted()) {
                int ret = ScannerKey.getKeyEvent();
                if (ret > -1) {
                    switch (ret) {
                        case ScannerKey.KEY_DOWN:
                            scanner.startScan();
                            break;
                        case ScannerKey.KEY_UP:
                            scanner.stopScan();
                            break;
                    }
                }
            }
//            System.out.println("关闭ScannerKey");
//            ScannerKey.close();
        }
//        System.out.println("关闭ScannerKey1");
//        ScannerKey.close();
    }

}
