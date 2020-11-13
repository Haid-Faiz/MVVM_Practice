package com.example.mvvm_prac.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Notes(@ColumnInfo(name = "title") val title: String) {   // as we set id to be auto generated so, we removed it from constructor.
    @PrimaryKey(autoGenerate = true) var id: Int = 0
    // we use @ColumnInfo(name = "your_column_name") to explicitly define the names of columns of table
    // but if you will not define it then automatically your variable name will be considered as column name.
}