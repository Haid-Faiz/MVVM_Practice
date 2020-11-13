package com.example.mvvm_prac.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_prac.Database.Notes
import com.example.mvvm_prac.R

class MyAdapter(private val context: Context, private val listener: NotesClickListener) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    val list: ArrayList<Notes> = ArrayList<Notes>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = list[position]
        holder.titleText.text = currentItem.title

        holder.deleteButton.setOnClickListener {
            listener.onDeleteClicked(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateAdapter(list: List<Notes>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleText = itemView.findViewById<TextView>(R.id.title)
        val deleteButton = itemView.findViewById<ImageButton>(R.id.delete_button)
    }
}

interface NotesClickListener {
    fun onDeleteClicked(notes: Notes)
}