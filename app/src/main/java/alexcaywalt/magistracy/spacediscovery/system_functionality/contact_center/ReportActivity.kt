package alexcaywalt.magistracy.spacediscovery.system_functionality.contact_center

import alexcaywalt.magistracy.spacediscovery.R
import alexcaywalt.magistracy.spacediscovery.Shared
import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_report.*
import kotlinx.android.synthetic.main.activity_report.clear
import kotlinx.android.synthetic.main.activity_report.submit
import kotlinx.android.synthetic.main.activity_select_problem.toolbar

class ReportActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        problem_type_image.setImageResource(Shared.currentProblemImageResource)

        submit.setOnClickListener {
            val result = if (report_message.text.isNotEmpty()) {
                "The message has been sent successfully"
            } else {
                "You did not enter a message"
            }
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
            clear.performClick()
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(report_message.windowToken, 0)
        }
        clear.setOnClickListener {
            report_message.text.clear()
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