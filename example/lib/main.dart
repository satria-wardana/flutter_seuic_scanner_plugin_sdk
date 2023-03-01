import 'dart:developer';

import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:flutter_seuic_scanner_plugin_sdk/BarcodeScanner.dart';
import 'package:flutter_seuic_scanner_plugin_sdk/flutter_seuic_scanner_plugin_sdk.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _barCode = 'null';
  String _result = 'empty';
  final _flutterSeuicScannerPluginSdkPlugin = FlutterSeuicScannerPluginSdk();

  @override
  void initState() {
    super.initState();
    initScanner();
  }

  void initScanner() {
    String barcode;
    _flutterSeuicScannerPluginSdkPlugin
        .getScanner()
        .listen((BarcodeScanner event) {
      barcode = event.barcode ?? '无';
      setState(() {
        _barCode = barcode;
      });
    });
  }

  Future<void> startScannerService() async {
    String result;
    try {
      result = await _flutterSeuicScannerPluginSdkPlugin.startScannerService();
    } on PlatformException {
      result = 'Failed to start scanner service.';
    }
    setState(() {
      _result = result;
    });
  }

  Future<void> stopScannerService() async {
    String result;
    try {
      result = await _flutterSeuicScannerPluginSdkPlugin.stopScannerService();
    } on PlatformException {
      result = 'Failed to stop scanner service.';
    }
    setState(() {
      _result = result;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
            child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text('Running on: $_result\n'),
            Text('扫描结果: $_barCode\n'),
            ElevatedButton(
                onPressed: () {
                  startScannerService();
                },
                child: const Text('start scanner service')),
            ElevatedButton(
                onPressed: () {
                  stopScannerService();
                },
                child: const Text('stop scanner service'))
          ],
        )),
      ),
    );
  }
}
