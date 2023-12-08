package com.example.myapplication.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapplication.databinding.ActivityEditBinding
import com.example.myapplication.room.utils.Note
import com.example.myapplication.room.utils.NoteDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding
    val db by lazy {NoteDB(this)}
    private var noteId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupListener()
    }

    private fun setupView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type", 0)
        when(intentType) {
            Constant.TYPE_CREATE -> {
                binding.buttonUpdate.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                binding.buttonSave.visibility = View.GONE
                binding.buttonUpdate.visibility = View.GONE
                getNote()
            }
            Constant.TYPE_UPDATE -> {
                binding.buttonSave.visibility = View.GONE
                getNote()
            }
        }
    }

    private fun setupListener() {
        binding.buttonSave.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.noteDao().addNote(
                    Note (0, binding.editTitle.text.toString(), binding.editNote.text.toString())
                )
                finish()
            }
        }
        binding.buttonUpdate.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.noteDao().updateNote(
                    Note (noteId, binding.editTitle.text.toString(), binding.editNote.text.toString())
                )
                finish()
            }
        }
    }

    private fun getNote() {
        noteId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val notes = db.noteDao().getNote(noteId)[0]
            binding.editTitle.setText( notes.title )
            binding.editNote.setText( notes.note )
        }
    }
}