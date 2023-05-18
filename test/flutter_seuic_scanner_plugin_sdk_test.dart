import 'package:flutter_seuic_scanner_plugin_sdk/barcode_scanner.dart';
import 'package:flutter_seuic_scanner_plugin_sdk/flutter_seuic_scanner_plugin_sdk_method_channel.dart';
import 'package:flutter_seuic_scanner_plugin_sdk/flutter_seuic_scanner_plugin_sdk_platform_interface.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockFlutterSeuicScannerPluginSdkPlatform
    with MockPlatformInterfaceMixin
    implements FlutterSeuicScannerPluginSdkPlatform {
  @override
  Stream<BarcodeScanner> getScanner() {
    // TODO: implement getScanner
    throw UnimplementedError();
  }

  @override
  Future<String> startScannerService() {
    // TODO: implement startScannerService
    throw UnimplementedError();
  }

  @override
  Future<String> stopScannerService() {
    // TODO: implement stopScannerService
    throw UnimplementedError();
  }
}

void main() {
  final FlutterSeuicScannerPluginSdkPlatform initialPlatform =
      FlutterSeuicScannerPluginSdkPlatform.instance;

  test('$MethodChannelFlutterSeuicScannerPluginSdk is the default instance',
      () {
    expect(initialPlatform,
        isInstanceOf<MethodChannelFlutterSeuicScannerPluginSdk>());
  });
}
