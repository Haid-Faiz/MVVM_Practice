package com.example.mvvm_prac.Database

import androidx.lifecycle.LiveData

// This repo class is a normal class, it isn't related to Architecture component
// it is just made to provide cleaner API
class Repository(private val notesDAO: NotesDAO) {

    val allNotes: LiveData<List<Notes>> = notesDAO.getAllNotes()

    suspend fun insert(notes: Notes){
        notesDAO.insert(notes)
    }

    suspend fun delete(notes: Notes){
        notesDAO.delete(notes)
    }
}