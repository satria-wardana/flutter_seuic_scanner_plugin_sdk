package com.seuic.scanner.flutter_seuic_scanner_plugin_sdk.model;


import java.io.Serializable;

public class BarcodeScanner implements Serializable {

    private static final long serialVersionUID = 3615204604863452229L;

    public BarcodeScanner() {}

    public BarcodeScanner(String barcode, String codeType, int length) {
        this.barcode = barcode;
        this.codeType = codeType;
        this.length = length;
    }

    private String barcode;
    private String codeType;
    private int length;

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "BarcodeScanner{" +
                "barcode='" + barcode + '\'' +
                ", codeType='" + codeType + '\'' +
                ", length=" + length +
                '}';
    }
}
