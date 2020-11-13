package com.example.mvvm_prac.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_prac.Adapter.MyAdapter
import com.example.mvvm_prac.Adapter.NotesClickListener
import com.example.mvvm_prac.Database.MyDatabase
import com.example.mvvm_prac.Database.NoteViewModel
import com.example.mvvm_prac.Database.Notes
import com.example.mvvm_prac.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), NotesClickListener {

    lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myAdapter = MyAdapter(this, this)
        recycler_view.adapter = myAdapter

        noteViewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)

        noteViewModel.allNotes.observe(this, Observer {list ->
            // when we have only one method in an interface then we can
            // directly write that interface like this Observer{ }
            list?.let {
                myAdapter.updateAdapter(it)
            }
        })

//        noteViewModel.allNotes.observe(this, object : Observer<List<Notes>>{
//            override fun onChanged(t: List<Notes>?) {
//
//            }
//        })
    }

    fun onItemInserted(view: View) {
        val title = text_input.editText?.text.toString().trim()

        if (title.isNotEmpty()){
            text_input.error = null
            text_input.editText?.text?.clear()
//            text_input.editText?.text = null
            noteViewModel.insert(Notes(title))


        }else{
            text_input.error = "Required"
        }
    }

    override fun onDeleteClicked(notes: Notes) {
        noteViewModel.delete(notes)
        Snackbar.make(recycler_view, "${notes.title} Deleted", Snackbar.LENGTH_SHORT).show()
    }
}