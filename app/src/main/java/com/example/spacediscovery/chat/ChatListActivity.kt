package com.example.spacediscovery.chat

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spacediscovery.DatabaseHandler
import com.example.spacediscovery.R
import kotlinx.android.synthetic.main.activity_chat_list.*

class ChatListActivity: AppCompatActivity() {

    private lateinit var chatsAdapter: ChatsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_list)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val db = DatabaseHandler(this)
        chatsAdapter = ChatsAdapter(db.getAllChats() as ArrayList<Chat>)
        db.close()
        chat_list.layoutManager = LinearLayoutManager(applicationContext)
        chat_list.adapter = chatsAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item!!.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

}
