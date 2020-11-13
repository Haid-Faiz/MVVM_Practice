package com.example.mvvm_prac.Database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)            // if we will enter same note again then OnConflictStrategy.INGNORE
    suspend fun insert(notes: Notes)                           // will allow us to add that note again.. you can also use
                                                               // ABORT, REPLACE

    @Delete
    suspend fun delete(notes: Notes)

    @Query("Select * from notes_table order by id ASC")
    fun getAllNotes(): LiveData<List<Notes>>


    // suspend keyword means: Now, this method can only be called from
    // background thread or by another suspend method.
    // This feature is from Kotlin CoRoutine
}