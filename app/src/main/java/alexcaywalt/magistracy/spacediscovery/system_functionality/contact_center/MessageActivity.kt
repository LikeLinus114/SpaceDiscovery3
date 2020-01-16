package alexcaywalt.magistracy.spacediscovery.system_functionality.contact_center

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

        val messageType = intent.getIntExtra("messageType", 3)
        when (messageType) {
            1 -> {
                toolbar.title = "Report"
                message_label.text = "Describe your problem:"
            }
            2 -> {
                toolbar.title = "Suggestion"
                message_label.text = "Describe your suggestion:"
            }
            else -> {
                toolbar.title = "Feedback"
                message_label.text = "Your opinion:"
            }
        }
        submit.setOnClickListener {
            val result = if (message.text.isNotEmpty()) {
                val intent = Intent(this, MessageSentActivity::class.java)
                intent.putExtra("messageType", messageType)
                startActivity(intent)
                "The message has been sent successfully"
            } else {
                "You did not enter a message"
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