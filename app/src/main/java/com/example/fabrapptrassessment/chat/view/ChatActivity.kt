package com.example.fabrapptrassessment.chat.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fabrapptrassessment.R
import com.example.fabrapptrassessment.chat.viewmodel.ChatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val chatViewModel : ChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        // set action bar
        setSupportActionBar(findViewById(R.id.chat_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // display the back -> button
        supportActionBar?.setTitle(R.string.chat_status) // update status title

        // setting up the recyclerview
        recyclerView = findViewById<RecyclerView?>(R.id.recyclerView).apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true) // do not change the layout size
        }

        chatViewModel.chat.observe(this, Observer {
            recyclerView.adapter = ChatAdapter(it)
        })
    }

    companion object {
        fun startIntent(context: Context) : Intent {
            return Intent(context, ChatActivity::class.java)
        }
    }
    //This method is called whenever the user chooses to navigate Up within
    // the application's activity hierarchy from the action bar.
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}