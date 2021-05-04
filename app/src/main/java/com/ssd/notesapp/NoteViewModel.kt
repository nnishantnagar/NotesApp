package com.ssd.notesapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//url: https://developer.android.com/codelabs/android-room-with-a-view-kotlin#9

//In this CLass AndroidViewModel inherited
class NoteViewModel(application: Application) : AndroidViewModel(application) {

    //
    private val repository:  NoteRepository
    val allNotes: LiveData<List<Notes>> // we got data in 'allNotes' variable from noteDao

    init {
        // first we make database variable
        // second we make Repository variable
        // third we get data from repository

        val dao = NoteDatabase.getDatabase(application).getNoteDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    fun deleteNote(note: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun insertNote(note: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

}