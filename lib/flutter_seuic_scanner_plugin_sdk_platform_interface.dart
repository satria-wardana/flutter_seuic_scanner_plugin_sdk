import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'BarcodeScanner.dart';
import 'flutter_seuic_scanner_plugin_sdk_method_channel.dart';

abstract class FlutterSeuicScannerPluginSdkPlatform extends PlatformInterface {
  /// Constructs a FlutterSeuicScannerPluginSdkPlatform.
  FlutterSeuicScannerPluginSdkPlatform() : super(token: _token);

  static final Object _token = Object();

  static FlutterSeuicScannerPluginSdkPlatform _instance = MethodChannelFlutterSeuicScannerPluginSdk();

  /// The default instance of [FlutterSeuicScannerPluginSdkPlatform] to use.
  ///
  /// Defaults to [MethodChannelFlutterSeuicScannerPluginSdk].
  static FlutterSeuicScannerPluginSdkPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [FlutterSeuicScannerPluginSdkPlatform] when
  /// they register themselves.
  static set instance(FlutterSeuicScannerPluginSdkPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }


  Stream<BarcodeScanner> getScanner() {
    throw UnimplementedError('getScannerCode() has not been implemented.');
  }

  Future<String> startScannerService(){
    throw UnimplementedError('startScannerService() has not been implemented.');
  }

  Future<String> stopScannerService(){
    throw UnimplementedError('stopScannerService() has not been implemented.');
  }
}
