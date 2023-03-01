

import 'package:flutter/services.dart';

class BarcodeScanner {
  late String barcode;
  late String codeType;
  late int length;

  Map<String, dynamic> toMap() {
    return {
      'barcode': barcode,
      'codeType': codeType,
      'length': length,
    };
  }

  static BarcodeScanner? fromMap(Map<String, dynamic>? map) {
    if (map == null) return null;
    return BarcodeScanner()
      ..barcode = map['barcode'] as String
      ..codeType = map['codeType'] as String
      ..length = map['length'] as int;

  }

  static const MethodCodec codec = JSONMethodCodec();
}