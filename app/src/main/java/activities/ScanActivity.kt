package activities

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.example.qrather.R
import com.example.qrather.db.DBHelper
import com.example.qrather.db.DBHelperI
import com.example.qrather.db.database.QrResultDatabase
import com.example.qrather.db.entities.QrResult
import com.example.qrather.ui.dialogs.QrCodeResultDialog
import java.util.*

class ScanActivity : AppCompatActivity() {
    lateinit var codeScanner: CodeScanner

    lateinit var resultDialog : QrCodeResultDialog
    private lateinit var dbHelperI : DBHelperI
    private  var result = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

        init()

        initViews()

//
//        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
//            PackageManager.PERMISSION_DENIED){
//            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 123)
//        }else{
//            startScanning()
//        }

        //for Debugging purpose
//        val qrResult =
//            QrResult(result = "Dummy Text", resultType ="TEXT", favourite = false, calendar = Calendar.getInstance())
//            QrResultDatabase.getAppDatabase(this)?.getQrDao()?.insertQrResult(qrResult)

    }
    private fun init() {
        dbHelperI = DBHelper(QrResultDatabase.getAppDatabase(this)!!)
    }
    private fun initViews() {
        startScanning()
        setResultDialog()
    }

    private fun setResultDialog() {
       resultDialog = QrCodeResultDialog(this)
    }

    private fun startScanning() {
        val scannedText = findViewById<TextView>(R.id.scannedText)
        val scannerView: CodeScannerView = findViewById(R.id.scanner_view)
        codeScanner = CodeScanner(this,scannerView)
        codeScanner.camera = CodeScanner.CAMERA_BACK
        codeScanner.formats = CodeScanner.ALL_FORMATS

        codeScanner.autoFocusMode = AutoFocusMode.SAFE
        codeScanner.scanMode = ScanMode.SINGLE
        codeScanner.isAutoFocusEnabled = true
        codeScanner.isFlashEnabled = false


        codeScanner.decodeCallback = DecodeCallback {
          runOnUiThread {
              result = it.text
//              scannedText.text = it.text
//              Toast.makeText(this, "Scan Result: ${it.text}", Toast.LENGTH_SHORT).show()

              onQrResult(result)
          }

        }
        codeScanner.errorCallback = ErrorCallback {
            runOnUiThread {

                Toast.makeText(this, "Camera initialization error: ${it.message}", Toast.LENGTH_SHORT).show()
            }
        }
        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    private fun onQrResult(text : String?) {
     if(text.isNullOrEmpty()){
         Toast.makeText(this,"Empty Qr Code",Toast.LENGTH_SHORT ).show()
     } else {
         saveToDatabase(text)
     }
    }

    private fun saveToDatabase(text : String) {
                val insertedRowId = dbHelperI.insertQRResult(result)
                val qrResult = dbHelperI.getQrResult(insertedRowId)
                resultDialog.show(qrResult)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ){ super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 123){
            if(grantResults[0] == PackageManager.PERMISSION_DENIED){
                Toast.makeText(this, "Camera permission granted", Toast.LENGTH_SHORT).show()
                startScanning()
            }else{
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (::codeScanner.isInitialized){
            codeScanner.startPreview()
        }
    }

    override fun onPause() {
        if (::codeScanner.isInitialized){
            codeScanner.releaseResources()
        }
        super.onPause()
    }
}