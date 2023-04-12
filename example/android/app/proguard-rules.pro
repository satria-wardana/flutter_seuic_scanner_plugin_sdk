# Seuic Scanner SDK
-keep class com.seuic.** { *; }
-dontwarn com.seuic.**

# Flutter wrapper
-keep class io.flutter.app.** { *; }
-keep class io.flutter.plugin.**  { *; }
-keep class io.flutter.util.**  { *; }
-keep class io.flutter.view.**  { *; }
-keep class io.flutter.**  { *; }
-keep class io.flutter.plugins.**  { *; }
-keep class androidx.lifecycle.** { *; }

# Gson
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.google.gson.examples.android.model.** { *; }
-keep class com.google.gson.** { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.internal.** { *; }
-keep class com.google.gson.reflect.** { *; }


# Apache Commons
-keep class org.apache.commons.** { *; }
-dontwarn org.apache.commons.logging.**

# OkHttp
-dontwarn okhttp3.**
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-keepattributes Signature
-keepattributes Annotation