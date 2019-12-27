package com.example.spacediscovery.chat

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spacediscovery.R
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_chat_list.*
import javax.inject.Inject

class ChatListActivity: AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModel: ChatViewModel

    private lateinit var chatsAdapter: ChatsAdapter

    override fun androidInjector() = androidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_list)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        chatsAdapter = ChatsAdapter(arrayListOf())
        chat_list.layoutManager = LinearLayoutManager(applicationContext)
        chat_list.adapter = chatsAdapter

        viewModel.fetchChats()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.chats.observe(this, Observer { chats ->
            chatsAdapter.updateChats(chats)
            if (chats.isEmpty()) {
                no_chats_label.visibility = View.VISIBLE
                no_chats_image.visibility = View.VISIBLE
            } else {
                no_chats_label.visibility = View.INVISIBLE
                no_chats_image.visibility = View.INVISIBLE
            }
        })
        viewModel.loading.observe(this, Observer { loading ->
            loading?.let { enableSpinner(it) }
        })
        viewModel.error.observe(this, Observer { error ->
            error?.let {
                if (it) {
                    no_chats_label.visibility = View.VISIBLE
                    no_chats_image.visibility = View.VISIBLE
                    Toast.makeText(this, "could not load the stations info", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun enableSpinner(isEnabled: Boolean) {
        if (isEnabled) {
            loading_spinner.visibility = View.VISIBLE
            shadow_view.visibility = View.VISIBLE
            no_chats_label.visibility = View.INVISIBLE
            no_chats_image.visibility = View.INVISIBLE
        } else {
            loading_spinner.visibility = View.GONE
            shadow_view.visibility = View.GONE
        }
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
