package ru.cryptolauncher

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val prefs = getSharedPreferences("crypto_launcher", MODE_PRIVATE)
        findViewById<TextView>(R.id.profileInfo).text =
            prefs.getString("profile", "1.21.4 · Fabric · Crypto Client")
        findViewById<Button>(R.id.btnPlay).setOnClickListener {
            Toast.makeText(this, "Runtime: integrate Pojav-class Java ME engine (GPL). UI ready.", Toast.LENGTH_LONG).show()
        }
        findViewById<Button>(R.id.btnProfiles).setOnClickListener {
            startActivity(Intent(this, ProfilesActivity::class.java))
        }
        findViewById<Button>(R.id.btnServers).setOnClickListener {
            startActivity(Intent(this, ServersActivity::class.java))
        }
        findViewById<Button>(R.id.btnPerf).setOnClickListener {
            startActivity(Intent(this, PerformanceActivity::class.java))
        }
        findViewById<Button>(R.id.btnMods).setOnClickListener {
            Toast.makeText(this, "Put jars in .minecraft/mods — manager UI next iteration", Toast.LENGTH_SHORT).show()
        }
    }
}
