package alexcaywalt.magistracy.spacediscovery.system_functionality.contact_center

import alexcaywalt.magistracy.spacediscovery.MESSAGE_TYPE
import alexcaywalt.magistracy.spacediscovery.R
import alexcaywalt.magistracy.spacediscovery.Shared
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_select_problem.*

class SelectProblemActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_problem)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        openReportActivity(location_problem, R.drawable.ic_location)
        openReportActivity(map_problem, R.drawable.ic_map)
        openReportActivity(celestial_bodies_problem, R.drawable.ic_planet)
        openReportActivity(stations_problem, R.drawable.ic_connection)
    }

    private fun openReportActivity(button: View, problemImageResource: Int) {
        button.setOnClickListener {
            Shared.currentMessageImageResource = problemImageResource
            val intent = Intent(this, MessageActivity::class.java)
            intent.putExtra(MESSAGE_TYPE, 1)
            startActivity(intent)
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
