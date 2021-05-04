package com.ssd.notesapp

import androidx.lifecycle.LiveData


//url: https://developer.android.com/codelabs/android-room-with-a-view-kotlin#8
// Repository:  Repository implements the logic
// It gives functionality of DAO functions


class NoteRepository(private val noteDao: NoteDao) {

    val allNotes: LiveData<List<Notes>> = noteDao.getAllNotes()

    suspend fun insert(note: Notes){
        noteDao.insert(note)
    }

    suspend fun delete(note: Notes){
        noteDao.delete(note)
    }

}