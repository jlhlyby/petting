# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/zongs/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}



#
#-optimizationpasses 5                                                       #指定代码压缩级别
#-dontusemixedcaseclassnames                                                #混淆时不会产生形形色色的类名
#-dontskipnonpubliclibraryclasses                                          #指定不忽略非公共类库
#-dontpreverify                                                              #不预校验，如果需要预校验，是-dontoptimize
#-ignorewarnings                                                             #屏蔽警告
#-verbose                                                                     #混淆时记录日志
#-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*    #优化
#-dontwarn android.support.v4.**                                             #去掉警告
#-keep interface android.support.v4.app.** { *; }
#-keep public class * extends android.support.v4.**
#-keep public class * extends android.app.Fragment
#-keep class android-support-v4.**{*;}
#
#-keep public class * extends android.support.v7.**
#
#-dontwarn  com.amap.api.**
#-keep class com.amap.api.location.**{*;}
#-keep class com.amap.api.fence.**{*;}
#-keep class com.autonavi.aps.amapapi.model.**{*;}