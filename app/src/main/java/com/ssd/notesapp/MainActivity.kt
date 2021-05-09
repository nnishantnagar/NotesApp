package com.ssd.notesapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import com.ssd.notesapp.NotesRVAdapter as NotesRVAdapter

/*  take a view of it to understand better for this project
    https://developer.android.com/codelabs/android-room-with-a-view-kotlin#5

 */

class MainActivity : AppCompatActivity(), INotesRVAdapter {

    // we initialized viewModel here
    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NotesRVAdapter(this, this)
        recyclerView.adapter = adapter

    // VMP determine our/custom made viewModel to get attached with owners/activity(in this case)  life-Cycle(create, delete)
        viewModel = ViewModelProvider(this,
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)

        viewModel.allNotes.observe(this, Observer {list ->
            list?.let {
                adapter.updateList(it)
            }
        })


    }

    override fun onItemClicked(note: Notes) {
       viewModel.deleteNote(note)
       // Toast.makeText(this, "${note.text} Deleted", Toast.LENGTH_SHORT).show()
            val builder=AlertDialog.Builder(this)
        builder.setTitle("Delete")
        builder.setMessage("Are you sure?")
        builder.setPositiveButton("Yes") { dialogInterface, which ->
        }
        builder.setNegativeButton("No") { dialogInterface, which ->
        }
            builder.setNeutralButton("cancel"){dialogInterface,which->
                Toast.makeText(this,"cancel",Toast.LENGTH_SHORT).show()

            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()

    }
    fun submitData(view: View) {
        val noteText = inputEditText.text.toString()
        if (noteText.isNotEmpty()){
            viewModel.insertNote(Notes(noteText))
           // Toast.makeText(this, "$noteText Inserted", Toast.LENGTH_SHORT).show()
            Snackbar.make(view,"Inserted",Snackbar.LENGTH_SHORT).setAction("Action",null).show()
        }
    }
}