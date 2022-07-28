package com.example.qrather.db.entities

import androidx.room.*
import com.example.qrather.db.converters.DateTimeConverters
import java.util.*

@Entity
@TypeConverters(DateTimeConverters::class)
data class QrResult(

    @PrimaryKey(autoGenerate = true)
    val id : Int ? = null,

    @ColumnInfo(name = "result")
    val result : String?,

    @ColumnInfo(name = "result_type")
    val resultType : String?,

    @ColumnInfo(name = "favourite")
    val favourite : Boolean,

    @ColumnInfo(name = "time")
    val calendar : Calendar
// studio don't know 'what calendar is'
    // we need type convertor [calendar to long and long to calendar]

)