package com.example.qrather.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.qrather.db.entities.QrResult

@Dao
interface QRResultDao {
    //here we implement all the queries and CRUD operations

    @Query("SELECT * FROM QrResult ORDER BY time DESC")
    fun getAllScannedResults() : List<QrResult>

    @Query("SELECT * FROM QrResult WHERE favourite = 1  ORDER BY time DESC" )
    fun getAllFavouriteResults() : List<QrResult>

    @Query("DELETE FROM QrResult")
    fun deletefromScannedResults()

    @Query("DELETE FROM QrResult WHERE favourite = 1")
    fun deletefromfavouriteResults()

    @Query("DELETE FROM QrResult WHERE id = :id")
    fun deleteQrResult(id: Int): Int

    @Query("DELETE FROM QrResult")
    fun deleteAllScannedResult()

    @Query("DELETE FROM QrResult WHERE favourite = 1")
    fun deleteAllFavouriteScannedResult()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQrResult(qrResult: QrResult): Long

    @Query("SELECT * FROM QrResult WHERE id = :id")
    fun getQrResults(id : Int) : QrResult

    @Query("UPDATE QrResult SET favourite = 1 WHERE id = :id")
    fun addToFavourite(id : Int) : Int

    @Query("UPDATE QrResult SET favourite = 0 WHERE id = :id")
    fun removeFromFavourite(id : Int) : Int

    @Query("SELECT * FROM QrResult WHERE result = :result ")
    fun checkIfQrResultExist(result: String): Int

}