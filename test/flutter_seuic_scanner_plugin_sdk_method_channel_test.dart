import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:flutter_seuic_scanner_plugin_sdk/flutter_seuic_scanner_plugin_sdk_method_channel.dart';

void main() {
  MethodChannelFlutterSeuicScannerPluginSdk platform = MethodChannelFlutterSeuicScannerPluginSdk();
  const MethodChannel channel = MethodChannel('flutter_seuic_scanner_plugin_sdk');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await platform.getPlatformVersion(), '42');
  });
}
