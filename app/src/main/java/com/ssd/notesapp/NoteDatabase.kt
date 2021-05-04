package com.ssd.notesapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



//url : https://developer.android.com/codelabs/android-room-with-a-view-kotlin#7



// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(Notes::class), version = 1, exportSchema = false)
public abstract class NoteDatabase : RoomDatabase() {

    abstract fun getNoteDao(): NoteDao

    /*  Here making it a singleton
        a Singleton have only 1 instance
     */
    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        // Making its instance
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {     // if INSTANCE is null then proceed
                val instance = Room.databaseBuilder(         // Synchronized block is here bcz koi or thread is mai enter na kre jab ye run kr rha ho
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}