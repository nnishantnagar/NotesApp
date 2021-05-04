package com.ssd.notesapp

import androidx.lifecycle.LiveData
import androidx.room.*


/* DAO = Data Access Object
    All SQL queries mentioned in DAO
    All operations in database are performed using DAO
    */


/*  using @Insert, @Delete, @Query makes them functions of DAO
    and Insert and Delete works in background thread(if it is not then an exception come)

    Q: Why Insert and Delete runs in background thread ??
    A: Because they performing I/O (input, output) operations and they are considered as heavy task and these heavy tasks
       make an app laggi
       so 'suspend' is used and it cannot call by a normal function
       Coroutines helps in handling in background threads
*/
@Dao
interface NoteDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE) // This onConflictStrategy.Ignore if same content repeats
//    This function takes note of type Notes(it's a class made by me) and insert it
    suspend fun insert(note: Notes)



    @Delete
//    This function delete notes of type Notes
    suspend fun delete(note: Notes)



    @Query("SELECT * from notes_table order by id ASC")     // This makes below function a fun. of DAO
//    This function collect all notes and return a List of type Notes
//    LiveData : It sends the information on Live basis to the activity
    fun getAllNotes(): LiveData<List<Notes>>


}