package alexcaywalt.magistracy.spacediscovery.system_functionality.contact_center

import alexcaywalt.magistracy.spacediscovery.MESSAGE_TYPE
import alexcaywalt.magistracy.spacediscovery.R
import alexcaywalt.magistracy.spacediscovery.Shared
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_message.*

class MessageActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        message_type_image.setImageResource(Shared.currentMessageImageResource)

        val messageType = intent.getIntExtra(MESSAGE_TYPE, 3)
        when (messageType) {
            1 -> {
                toolbar.title = resources.getString(R.string.report)
                message_label.text = resources.getString(R.string.report_describe)
            }
            2 -> {
                toolbar.title = resources.getString(R.string.suggestion)
                message_label.text = resources.getString(R.string.suggestion_describe)
            }
            else -> {
                toolbar.title = resources.getString(R.string.feedback)
                message_label.text = resources.getString(R.string.feedback_describe)
            }
        }
        submit.setOnClickListener {
            val result = if (message.text.isNotEmpty()) {
                val intent = Intent(this, MessageSentActivity::class.java)
                intent.putExtra(MESSAGE_TYPE, messageType)
                startActivity(intent)
                resources.getString(R.string.message_sent_successfully)
            } else {
                resources.getString(R.string.message_empty)
            }
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
            clear.performClick()
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(message.windowToken, 0)
        }
        clear.setOnClickListener {
            message.text.clear()
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