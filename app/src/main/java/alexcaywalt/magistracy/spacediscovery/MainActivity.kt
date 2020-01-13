package alexcaywalt.magistracy.spacediscovery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button: Button = findViewById(R.id.login_button)
        button.setOnClickListener {
            startActivity(Intent(this@MainActivity, SidePane::class.java))
        }
        closeActiveChats()
    }

    private fun closeActiveChats() {
        val db = DatabaseHandler(this)
        val chats = db.getAllChats() as ArrayList
        chats.forEach {
            it.isActive = false
        }
        //update the DB
        db.deleteAll()
        chats.forEach {
            db.addChat(it)
        }
        db.close()
    }
}
