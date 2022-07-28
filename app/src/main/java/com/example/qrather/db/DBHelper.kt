package com.example.qrather.db

import com.example.qrather.db.database.QrResultDatabase
import com.example.qrather.db.entities.QrResult
import java.util.*

class DBHelper(var qrResultDatabase : QrResultDatabase) : DBHelperI {

    override fun insertQRResult(result : String) : Int {

        val time = Calendar.getInstance()
        val resultType = "TEXT"
        val qrResult = QrResult(result = result, resultType = resultType, calendar = time, favourite = false)
        return qrResultDatabase.getQrDao().insertQrResult(qrResult).toInt()
    }

    override fun getQrResult(id : Int) : QrResult {
        return qrResultDatabase.getQrDao().getQrResults(id)
    }

    override fun addToFavourite(id : Int) : Int {
        return qrResultDatabase.getQrDao().addToFavourite(id)
    }

    override fun removeFromFavourite(id : Int) : Int {
        return qrResultDatabase.getQrDao().removeFromFavourite(id)
    }

    override fun getAllScannedResult() : List<QrResult> {
          return qrResultDatabase.getQrDao().getAllScannedResults()
    }

    override fun getAllFavouriteQrScannedResult() : List<QrResult> {
       return qrResultDatabase.getQrDao().getAllFavouriteResults()
    }

    override fun deleteQrResult(id : Int) : Int {
        return qrResultDatabase.getQrDao().deleteQrResult(id)
    }

    override fun deleteAllQRScannedResult() {
         qrResultDatabase.getQrDao().deleteAllScannedResult()
    }

    override fun deleteAllFavouriteQRScannedResult() {
        qrResultDatabase.getQrDao().deleteAllFavouriteScannedResult()
    }
}