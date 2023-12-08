package com.example.myapplication.room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.MainActivity
import com.example.myapplication.databinding.ActivityRoomBinding
import com.example.myapplication.room.utils.Note
import com.example.myapplication.room.utils.NoteDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RoomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoomBinding
    val db by lazy { NoteDB(this) }
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListener()
        setupRecyclerView()
        action()
    }

    private fun action() {
        binding.kembali.setOnClickListener {
            startActivity(Intent(this@RoomActivity, MainActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        loadNote()
    }

    private fun loadNote() {
        CoroutineScope(Dispatchers.IO).launch {
            val notes = db.noteDao().getNotes()
            Log.d("RoomActivity", "dbResponse: $notes")
            withContext(Dispatchers.Main) {
                noteAdapter.setData(notes)
            }
        }
    }

    private fun setupListener() {
        binding.buttonCreate.setOnClickListener {
            intentEdit(0,Constant.TYPE_CREATE)
        }
    }

    private fun intentEdit(noteId: Int, intentType: Int) {
        startActivity(
            Intent(
            this@RoomActivity, EditActivity::class.java)
            .putExtra("intent_id", noteId)
            .putExtra("intent_type", intentType)
        )
    }

    private fun setupRecyclerView() {
        noteAdapter = NoteAdapter(arrayListOf(), object : NoteAdapter.OnAdapterListener{
            override fun onRead(note: Note) {
                // Toast.makeText(applicationContext, note.title, Toast.LENGTH_SHORT).show()
                //startActivity(Intent(
                //    applicationContext, EditActivity::class.java)
                //    .putExtra("intent_id", note.id)
                //)
                intentEdit(note.id,Constant.TYPE_READ)
            }

            override fun onUpdate(note: Note) {
                intentEdit(note.id,Constant.TYPE_UPDATE)
            }

            override fun onDelete(note: Note) {
                deleteDialog(note)
            }

        })
        binding.listNote.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = noteAdapter
        }
    }

    private fun deleteDialog(note: Note) {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("Konfirmasi")
            setMessage("Yakin hapus ${note.title} ?")
            setNegativeButton("Batal") { dialog, which ->
                dialog.dismiss()
            }
            setPositiveButton("Hapus") { dialog, which ->
                dialog.dismiss()
                CoroutineScope(Dispatchers.IO).launch {
                    db.noteDao().deleteNote(note)
                    loadNote()
                }
            }
        }
        alertDialog.show()
    }
}