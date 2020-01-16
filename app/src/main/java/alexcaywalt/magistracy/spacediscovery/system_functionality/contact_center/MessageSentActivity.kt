package alexcaywalt.magistracy.spacediscovery.system_functionality.contact_center

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

        when (intent.getIntExtra("messageType", 3)) {
            1 -> {
                toolbar.title = "Report"
                message_label.text = "Your report has been sent!\nWe will answer you as soon as possible."
            }
            2 -> {
                toolbar.title = "Suggestion"
                message_label.text = "Your suggestion has been sent!\nWe will answer you as soon as possible."
            }
            else -> {
                toolbar.title = "Feedback"
                message_label.text = "Your feedback has been sent!\nThanks!"
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