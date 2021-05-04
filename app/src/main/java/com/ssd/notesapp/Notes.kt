package com.ssd.notesapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Entity means table in sqlite
@Entity(tableName = "notes_table")
class Notes(@ColumnInfo(name = "text")val text: String) {

    //for auto-generating id numbers
    @PrimaryKey(autoGenerate = true) var id = 0




}