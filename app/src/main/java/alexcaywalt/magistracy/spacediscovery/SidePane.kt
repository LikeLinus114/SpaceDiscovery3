package alexcaywalt.magistracy.spacediscovery

import alexcaywalt.magistracy.spacediscovery.bodies_and_satellites.BodiesSatellitesFragment
import alexcaywalt.magistracy.spacediscovery.chat.ChatListActivity
import alexcaywalt.magistracy.spacediscovery.galaxy_map.GalaxyMapFragment
import alexcaywalt.magistracy.spacediscovery.location.LocationFragment
import alexcaywalt.magistracy.spacediscovery.stations.StationsFragment
import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class SidePane : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_side_pane)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        toolbar.title = "Space Discovery"
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        var snackbar: Snackbar? = null
        fab.setOnClickListener { view ->
            if (snackbar != null && snackbar!!.isShown) {
                snackbar!!.dismiss()
            } else {
                snackbar = Snackbar.make(view, "No new messages", Snackbar.LENGTH_INDEFINITE)
                snackbar!!.view.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
                snackbar!!.setActionTextColor(ContextCompat.getColor(this, R.color.colorYellow))
                snackbar!!.setAction("Go to chat") {
                    snackbar!!.dismiss()
                    startActivity(Intent(this, ChatListActivity::class.java))
                }
                snackbar!!.show()
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        openSection(DescriptionFragment())
        navView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.side_pane, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.update -> {
                val fragment = supportFragmentManager.findFragmentByTag("fragmentTag")!!
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.detach(fragment).attach(fragment).commit()
                true
            }
            R.id.home -> {
                openSection(DescriptionFragment())
                true
            }
            R.id.action_logout -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.locate_current_position -> {
                // Handle the camera action
                openSection(LocationFragment())
            }
            R.id.map_of_galaxy -> {
                openSection(GalaxyMapFragment())
            }
            R.id.celestial_bodies_and_satellites -> {
                openSection(BodiesSatellitesFragment())
            }
            R.id.connect_to_stations -> {
                openSection(StationsFragment())
            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun openSection(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.section, fragment, "fragmentTag")
        fragmentTransaction.commit()
    }
}
