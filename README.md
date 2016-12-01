# Android App: "QuickSet SDK Test"


### What's this:

This is a proof-of-concept Android app which is targeted especially for the LeEco Le Pro 3 (LEX720 / LEX727) phone running EUI 5.8.018. This app shows how to use the Infrared blaster on the LeEco Le Pro 3.


### Short Background

The Le Pro 3 does not support the default Android [Infrared API](https://developer.android.com/reference/android/hardware/ConsumerIrManager.html) as it doesn't just contain a simple IR emitter LED, but instead contains a complex system-on-a-chip Infrared solution from Universal Electronics, which supports not only sending, but also receiving infrared ("learning mode").

Instead of the Android Infrared API this device uses the QuickSet SDK (from Universal Electronics) which is running as a Service (`com.uei.control.Service`) which is provided by the package `com.uei.quicksetsdk.letv` (*/system/app/UEIQuicksetSDKLeTV/UEIQuicksetSDKLeTV.apk*).

This app here tries to bind this UEI Control Service and uses its API to transmit raw IR patterns instead of the Android [ConsumerIrManager API](https://developer.android.com/reference/android/hardware/ConsumerIrManager.html).

### Root permssion

Unfortunately the Le Pro 3 also has a kind of "master switch" to enable or disable the IR blaster. This i done by echo-ing a `1` or a `0` into the sys-file `/sys/remote/enable`. While the file permissions look the file would be world-writable (`-rw-rw-rw-`) in reality it is protected by SELinux policy. The original LeEco remote control app is allowed by SE policy to write into this file.
To overcome this issue, this app uses Super SU permissions to write `1` into this sys file.


### Find more information on XDA:

See this **thread on XDA developers** for more details: [[REF] How Infrared is (not) working on LePro3 - some infos for IR devs](http://forum.xda-developers.com/le-pro3/development/ref-how-infrared-lepro3-infos-ir-devs-t3506257)



### Tested with:

This module was developed and tested on the following device:

- Device **LeEco LePro 3 (LEX720)**
- Firmware version: **5.8.018S**
- Build-ID: **WAXCNFN5801811012S**

It may or may not work on other EUI versions.


### Support:
I don't directly give support, but you may look into the [related thread on the XDA developers forum](http://forum.xda-developers.com/le-pro3/development/ref-how-infrared-lepro3-infos-ir-devs-t3506257). 

Please give also feedback there, if you find the app working for other EUI versions or if you are using this code in your own app. Thanks. :)
