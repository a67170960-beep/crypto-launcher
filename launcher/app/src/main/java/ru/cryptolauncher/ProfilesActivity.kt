package ru.cryptolauncher

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfilesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(0xFF0A0E16.toInt())
            setPadding(32, 48, 32, 32)
        }
        val prefs = getSharedPreferences("crypto_launcher", MODE_PRIVATE)
        root.addView(TextView(this).apply {
            text = "Profiles"; setTextColor(0xFF6FA8FF.toInt()); textSize = 20f
        })
        val profiles = listOf(
            "1.21.4 · Fabric · Crypto Client (default)",
            "1.21.4 · Fabric · Vanilla HUD",
            "1.20.4 · Fabric"
        )
        profiles.forEach { p ->
            root.addView(Button(this).apply {
                text = p; textAllCaps = false
                setOnClickListener {
                    prefs.edit().putString("profile", p).apply()
                    finish()
                }
            })
        }
        root.addView(Button(this).apply { text = "Back"; setOnClickListener { finish() } })
        setContentView(root)
    }
}
