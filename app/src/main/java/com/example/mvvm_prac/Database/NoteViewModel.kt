package com.example.mvvm_prac.Database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    val allNotes: LiveData<List<Notes>>
    private val repository: Repository

    init {
        val database = MyDatabase.getMyDatabase(application)
        val dao = database.getNotesDao()
        // val dao = MyDatabase.getMyDatabase(application).getNotesDao()
        repository = Repository(dao)
        allNotes =  repository.allNotes
    }

    fun insert(notes: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(notes)
    }

    fun delete(notes: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(notes)
    }
}