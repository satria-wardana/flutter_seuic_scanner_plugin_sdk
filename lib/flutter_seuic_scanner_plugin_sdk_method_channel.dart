import 'dart:convert';

import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';
import 'package:flutter_seuic_scanner_plugin_sdk/barcode_scanner.dart';
import 'package:flutter_seuic_scanner_plugin_sdk/seuic_scanner_channel.dart';

import 'flutter_seuic_scanner_plugin_sdk_platform_interface.dart';

/// An implementation of [FlutterSeuicScannerPluginSdkPlatform] that uses method channels.
class MethodChannelFlutterSeuicScannerPluginSdk
    extends FlutterSeuicScannerPluginSdkPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('flutter_seuic_scanner_plugin_sdk');

  @visibleForTesting
  final eventChannel = EventChannel(SeuicScannerChannel.scannerEventChannel);

  @override
  Stream<BarcodeScanner> getScanner() {
    return eventChannel
        .receiveBroadcastStream()
        .map((dynamic event) => BarcodeScanner.fromMap(jsonDecode(event))!);
  }

  @override
  Future<String> startScannerService() async {
    return await methodChannel
        .invokeMethod(SeuicScannerChannel.startMethodChannel);
  }

  @override
  Future<String> stopScannerService() async {
    return await methodChannel
        .invokeMethod(SeuicScannerChannel.stopMethodChannel);
  }
}
