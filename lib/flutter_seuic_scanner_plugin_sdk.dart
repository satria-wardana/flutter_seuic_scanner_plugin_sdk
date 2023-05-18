import 'barcode_scanner.dart';
import 'flutter_seuic_scanner_plugin_sdk_platform_interface.dart';

class FlutterSeuicScannerPluginSdk {
  Stream<BarcodeScanner> getScanner() {
    return FlutterSeuicScannerPluginSdkPlatform.instance.getScanner();
  }

  Future<String> startScannerService() {
    return FlutterSeuicScannerPluginSdkPlatform.instance.startScannerService();
  }

  Future<String> stopScannerService() {
    return FlutterSeuicScannerPluginSdkPlatform.instance.stopScannerService();
  }
}
