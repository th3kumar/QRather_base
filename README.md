# QRather_base
**QRather** is a Quick Response (QR) Code scanning library for Android that is based on CameraX and ML Kit on-device barcode detection. It's an alternative to ZXing based libraries and written in Kotlin.

![Pixel True Mockup high](https://user-images.githubusercontent.com/72141924/191039525-968e87a9-94ae-48a9-ae23-22cea8da6308.png)



> **Note**: At Google I/O 2022 the [Google code scanner](https://developers.google.com/ml-kit/code-scanner) was announced. You should consider using it instead of qrather. If you want to support devices without Play Services or like to ship the latest ML Kit model - use qrather.


## Quick Start

#### View-based
To use the QR scanner simply register the `ScanQRCode()` ActivityResultContract together with a callback during `init` or `onCreate()` lifecycle of your Activity/Fragment and use the returned ActivityResultLauncher to launch the QR scanner Activity.
```kotlin
val scanQrCodeLauncher = registerForActivityResult(ScanQRCode()) { result ->
    // handle QRResult
}

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    …
    binding.button.setOnClickListener { scanQrCodeLauncher.launch(null) }
}
```

Check out the [sample](https://github.com/G00fY2/quickie/tree/develop/sample) inside this repo or visit the official [Activity Result API documentation](https://developer.android.com/training/basics/intents/result) for more information.

#### Jetpack Compose
Use the `rememberLauncherForActivityResult()` API to register the `ScanQRCode()` ActivityResultContract together with a callback in your composable:
```kotlin
@Composable
fun GetQRCodeExample() {
    val scanQrCodeLauncher = rememberLauncherForActivityResult(ScanQRCode()) { result ->
        // handle QRResult
    }
    
    Button(onClick = { scanQrCodeLauncher.launch(null) }) {
    …
}
```
Check out the official [Compose Activity Result documentation](https://developer.android.com/jetpack/compose/libraries#activity_result) for more information.

### Responses
The activity result is a subclass of the sealed `QRResult` class: 

1. `QRSuccess` when ML Kit successfully detected a QR code
   * wraps a `QRContent` object
1. `QRUserCanceled` when the Activity got canceled by the user
1. `QRMissingPermission` when the user didn't accept the camera permission
1. `QRError` when CameraX or ML Kit threw an exception
   * wraps the `exception`

#### Content
The content type of the QR code detected by ML Kit is wrapped inside a subclass of the sealed `QRContent` class which always provides a `rawValue`.

Currently, supported subtypes are:
`Plain`, `Wifi`, `Url`, `Sms`, `GeoPoint`, `Email`, `Phone`, `ContactInfo`, `CalendarEvent`

See the ML Kit [Barcode documentation](https://developers.google.com/android/reference/com/google/mlkit/vision/barcode/common/Barcode#nested-class-summary) for further details.

### Customization
Use the `ScanCustomCode()` ActivityResultContract to create a configurable barcode scan. When launching the ActivityResultLauncher pass in a `ScannerConfig` object:

<details>
  <summary>BarcodeFormat options</summary>

```kotlin
BarcodeFormat.FORMAT_ALL_FORMATS
BarcodeFormat.FORMAT_CODE_128
BarcodeFormat.FORMAT_CODE_39
BarcodeFormat.FORMAT_CODE_93
BarcodeFormat.FORMAT_CODABAR
BarcodeFormat.FORMAT_DATA_MATRIX
BarcodeFormat.FORMAT_EAN_13
BarcodeFormat.FORMAT_EAN_8
BarcodeFormat.FORMAT_ITF
BarcodeFormat.FORMAT_QR_CODE
BarcodeFormat.FORMAT_UPC_A
BarcodeFormat.FORMAT_UPC_E
BarcodeFormat.FORMAT_PDF417
BarcodeFormat.FORMAT_AZTEC
```
</details>

```kotlin
val scanCustomCode = registerForActivityResult(ScanCustomCode(), ::handleResult)

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    …
    binding.button.setOnClickListener {
      scanCustomCode.launch(
        ScannerConfig.build {
          setBarcodeFormats(listOf(BarcodeFormat.FORMAT_CODE_128)) // set interested barcode formats
          setOverlayStringRes(R.string.scan_barcode) // string resource used for the scanner overlay
          setOverlayDrawableRes(R.drawable.ic_scan_barcode) // drawable resource used for the scanner overlay
          setHapticSuccessFeedback(false) // enable (default) or disable haptic feedback when a barcode was detected
          setShowTorchToggle(true) // show or hide (default) torch/flashlight toggle button
          setHorizontalFrameRatio(2.2f) // set the horizontal overlay ratio (default is 1 / square frame)
          setUseFrontCamera(true) // use the front camera
        }
      )
    }
}

fun handleResult(result: QRResult) {
    …
```
:bulb: You can optionally [pass in an ActivityOptionsCompat object](https://developer.android.com/reference/androidx/activity/result/ActivityResultLauncher#launch(I,%20androidx.core.app.ActivityOptionsCompat)) when launching the ActivityResultLauncher to control the scanner launch animation.

## Libraries Used

. [BarCodeScanner](https://github.com/dm77/barcodescanner)

. [Room Database](https://developer.android.com/training/data-storage/room)

. [Google Material Design](https://material.io/develop/android/docs/getting-started/)

. [Stetho](http://facebook.github.io/stetho/)

