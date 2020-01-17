package alexcaywalt.magistracy.spacediscovery.system_functionality.contact_center

import alexcaywalt.magistracy.spacediscovery.MESSAGE_TYPE
import alexcaywalt.magistracy.spacediscovery.R
import alexcaywalt.magistracy.spacediscovery.Shared
import alexcaywalt.magistracy.spacediscovery.SidePane
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_message_sent.*

class MessageSentActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_sent)

        when (intent.getIntExtra(MESSAGE_TYPE, 3)) {
            1 -> {
                toolbar.title = resources.getString(R.string.report)
                message_label.text = resources.getString(R.string.report_sent)
            }
            2 -> {
                toolbar.title = resources.getString(R.string.suggestion)
                message_label.text = resources.getString(R.string.suggestion_sent)
            }
            else -> {
                toolbar.title = resources.getString(R.string.feedback)
                message_label.text = resources.getString(R.string.feedback_sent)
            }
        }

        problem_type_image.setImageResource(Shared.currentMessageImageResource)
        ok_button.setOnClickListener {
            val intent = Intent(this, SidePane::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        ok_button.performClick()
    }

}