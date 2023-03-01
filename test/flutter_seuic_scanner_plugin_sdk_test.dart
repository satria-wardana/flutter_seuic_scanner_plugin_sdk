import 'package:flutter_test/flutter_test.dart';
import 'package:flutter_seuic_scanner_plugin_sdk/flutter_seuic_scanner_plugin_sdk.dart';
import 'package:flutter_seuic_scanner_plugin_sdk/flutter_seuic_scanner_plugin_sdk_platform_interface.dart';
import 'package:flutter_seuic_scanner_plugin_sdk/flutter_seuic_scanner_plugin_sdk_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockFlutterSeuicScannerPluginSdkPlatform
    with MockPlatformInterfaceMixin
    implements FlutterSeuicScannerPluginSdkPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final FlutterSeuicScannerPluginSdkPlatform initialPlatform = FlutterSeuicScannerPluginSdkPlatform.instance;

  test('$MethodChannelFlutterSeuicScannerPluginSdk is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelFlutterSeuicScannerPluginSdk>());
  });

  test('getPlatformVersion', () async {
    FlutterSeuicScannerPluginSdk flutterSeuicScannerPluginSdkPlugin = FlutterSeuicScannerPluginSdk();
    MockFlutterSeuicScannerPluginSdkPlatform fakePlatform = MockFlutterSeuicScannerPluginSdkPlatform();
    FlutterSeuicScannerPluginSdkPlatform.instance = fakePlatform;

    expect(await flutterSeuicScannerPluginSdkPlugin.getPlatformVersion(), '42');
  });
}
