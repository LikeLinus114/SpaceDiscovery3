package alexcaywalt.magistracy.spacediscovery.main_functionality.stations.chat

import alexcaywalt.magistracy.spacediscovery.DatabaseHandler
import alexcaywalt.magistracy.spacediscovery.R
import alexcaywalt.magistracy.spacediscovery.Shared
import alexcaywalt.magistracy.spacediscovery.main_functionality.stations.chat.models.Chat
import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity: AppCompatActivity() {

    private lateinit var adapter: MessagesAdapter
    private var db: DatabaseHandler? = null
    private var allChats: Set<Chat>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        interlocutor.text = Shared.currentStation!!.name
        station_image.setImageBitmap(Shared.currentStation!!.imageBitMap)

        initChat()
        listOf(new_message, submit, clear).forEach {
            if (Shared.currentChat!!.isActive) {
                it.visibility = View.VISIBLE
            } else {
                it.visibility = View.INVISIBLE
            }
        }
        adapter = MessagesAdapter(Shared.currentChat!!.messages)
        messages_list.layoutManager = LinearLayoutManager(applicationContext)
        messages_list.adapter = adapter

        submit.setOnClickListener {
            val result = Shared.currentChat!!.addMessage(new_message.text.toString(), "me")
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
            clear.performClick()
            //close the on-screen keyboard
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(new_message.windowToken, 0)

            empty_messages_history_label.visibility = View.GONE
            messages_list.smoothScrollToPosition(Shared.currentChat!!.messages.size - 1)
        }
        clear.setOnClickListener {
            new_message.text.clear()
        }
    }

    private fun initChat() {
        db = DatabaseHandler(this)
        allChats = db!!.getAllChats().toHashSet()
        if (Shared.currentChat == null) {
            Shared.currentChat = Chat(
                Shared.currentStation!!.copyWithNoImage(),
                arrayListOf(),
                true
            )
            empty_messages_history_label.visibility = View.VISIBLE
        } else {
            allChats = allChats!!.minus(Shared.currentChat!!)
            empty_messages_history_label.visibility = View.GONE
            messages_list.smoothScrollToPosition(Shared.currentChat!!.messages.size - 1)
        }
    }

    override fun finish() {
        if (Shared.currentChat!!.messages.isNotEmpty()) {
            allChats = allChats!!.plus(Shared.currentChat!!)
            //update the DB
            db!!.deleteAll()
            allChats!!.forEach {
                db!!.addChat(it)
            }
        }
        db!!.close()
        super.finish()
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