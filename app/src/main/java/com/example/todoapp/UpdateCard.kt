package com.example.todoapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UpdateCard : AppCompatActivity() {
    private lateinit var database: myDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_card)
        database= Room.databaseBuilder(
            applicationContext,myDatabase::class.java,"To_Do"
        ).build()
        val pos = intent.getIntExtra("id", -1)

        // Find the add and deleteAll buttons by their IDs
        val createTitle : TextView = findViewById(R.id.create_title)
        val createPriority: TextView = findViewById(R.id.create_priority)
        val updateButton: Button = findViewById(R.id.update_button)
        val deleteButton: Button = findViewById(R.id.delete_button)

        if (pos != -1) {
            val title = DataObject.getData(pos).title
            val priority = DataObject.getData(pos).priority
            createTitle.text = title
            createPriority.text = priority

            deleteButton.setOnClickListener {
                DataObject.deleteData(pos)
                GlobalScope.launch {
                    database.dao().deleteTask(
                        Entity(pos+1,title,priority)
                    )
                }
                myIntent()
            }


            updateButton.setOnClickListener {
                val updatedTitle = createTitle.text.toString()
                val updatedPriority = createPriority.text.toString()
                DataObject.updateData(
                    pos,
                    updatedTitle,
                    updatedPriority
                )
                GlobalScope.launch {
                    database.dao().updateTask(
                        Entity(pos+1,updatedTitle,
                            updatedPriority)
                    )
                }
                Toast.makeText(this, "$updatedTitle $updatedPriority", Toast.LENGTH_LONG).show()
                myIntent()
            }
        }
    }

    private fun myIntent() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}