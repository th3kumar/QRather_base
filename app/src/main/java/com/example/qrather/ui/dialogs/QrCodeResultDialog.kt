package com.example.qrather.ui.dialogs

import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.qrather.R
import com.example.qrather.db.DBHelper
import com.example.qrather.db.DBHelperI
import com.example.qrather.db.database.QrResultDatabase
import com.example.qrather.db.entities.QrResult
import com.example.qrather.ui.utils.ContentCheckUtil.isWebUrl
import com.example.qrather.ui.utils.toFormatedDisplay


class QrCodeResultDialog(var context : Context) {


    private lateinit var dialog : Dialog
     private lateinit var dbHelperI : DBHelperI
    private var qrResult : QrResult? = null

    init {
       init()
        initDialog()
    }

    private fun init() {
        dbHelperI = DBHelper(QrResultDatabase.getAppDatabase(context)!!)
    }

    private fun initDialog() {

        dialog = Dialog(context)
        dialog.setContentView(R.layout.layout_qr_result_show)
        dialog.setCancelable(false)
        onClicks()
    }

    fun show(qrResult : QrResult){
        val scannedDate = dialog.findViewById<TextView>(R.id.scannedDate)
        val favouriteIcon = dialog.findViewById<ImageView>(R.id.favouriteIcon)
        var copyResult = dialog.findViewById<ImageView>(R.id.copyResult)
        var shareResult = dialog.findViewById<ImageView>(R.id.shareResult)
        var cancelDialog = dialog.findViewById<ImageView>(R.id.cancelDialog)
        val scannedText = dialog.findViewById<TextView>(R.id.scannedText)

        this.qrResult = qrResult
        scannedDate.text = qrResult.calendar.toFormatedDisplay()
        scannedText.text = qrResult.result
        favouriteIcon.isSelected =qrResult.favourite
        dialog.show()
    }
    private fun onClicks() {

        val favouriteIcon = dialog.findViewById<ImageView>(R.id.favouriteIcon)
        val copyResult = dialog.findViewById<ImageView>(R.id.copyResult)
        val shareResult = dialog.findViewById<ImageView>(R.id.shareResult)
        val cancelDialog = dialog.findViewById<ImageView>(R.id.cancelDialog)
        val scannedText = dialog.findViewById<TextView>(R.id.scannedText)


        favouriteIcon.setOnClickListener {
            if (it.isSelected) {
                removeFromFavourite()
            } else
                addToFavourite()
        }

        copyResult.setOnClickListener {
            copyResultToClipBoard()
        }

        shareResult.setOnClickListener {
            shareResult()
        }

        cancelDialog.setOnClickListener {
            dialog.dismiss()
           // onDismissListener?.onDismiss()
        }

        scannedText.setOnClickListener {
            checkContentAndPerformAction(scannedText.text.toString())
        }




    }

    private fun shareResult() {
        val scannedText = dialog.findViewById<TextView>(R.id.scannedText)
        val txtIntent = Intent(Intent.ACTION_SEND)
        txtIntent.type ="text/plain"
        txtIntent.putExtra(Intent.EXTRA_TEXT,scannedText.text.toString())
        context.startActivity(txtIntent)
    }

    private fun addToFavourite()  {
        //lateinit var dbHelperI : DBHelperI
        val favouriteIcon = dialog.findViewById<ImageView>(R.id.favouriteIcon)


        favouriteIcon.isSelected = true
        dbHelperI.addToFavourite(qrResult?.id!!)
    }

    private fun checkContentAndPerformAction(scannedText : String) {
        when {

            // if it is web url
            isWebUrl(scannedText) -> {

                // opening web url.
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(scannedText)
                context.startActivity(i)
            }
        }


    }

    private fun copyResultToClipBoard() {

        val scannedText = dialog.findViewById<TextView>(R.id.scannedText)

        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("QrScannerResult",scannedText.text)
        clipboard.text = clip.getItemAt(0).text.toString()
        Toast.makeText(context,"Copied to clipboard",Toast.LENGTH_SHORT).show()

    }

    private fun removeFromFavourite() {
        val favouriteIcon = dialog.findViewById<ImageView>(R.id.favouriteIcon)

        favouriteIcon.isSelected = false
        dbHelperI.removeFromFavourite(qrResult?.id!!)
    }

}