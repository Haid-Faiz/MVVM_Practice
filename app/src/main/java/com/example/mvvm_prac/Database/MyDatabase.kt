package com.example.mvvm_prac.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Notes::class), version = 1, exportSchema = false)  //we can also add more than 1 entity in this array.
abstract class MyDatabase : RoomDatabase() {

    abstract fun getNotesDao(): NotesDAO

    companion object {

        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getMyDatabase(context: Context): MyDatabase {
            // if the instance is not null then return it.
            // & if it is null then create Database  (MyDatabase instance)

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        MyDatabase::class.java,
                        "note_database"
                ).build()

                INSTANCE = instance

                // returning instance
                instance
            }
        }
    }
}