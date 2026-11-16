package ru.cryptolauncher

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PerformanceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(0xFF0A0E16.toInt())
            setPadding(32, 48, 32, 32)
        }
        val title = TextView(this).apply {
            text = "Crypto Performance Engine"
            setTextColor(0xFF6FA8FF.toInt()); textSize = 20f
        }
        val info = TextView(this).apply {
            setTextColor(0xFFAAAAAA.toInt()); textSize = 13f
            text = "Profiles map to JVM/render hints when runtime is present.\nRAM: device-aware suggestion only."
        }
        val group = RadioGroup(this)
        val modes = listOf("Battery Saver", "Balanced", "Performance", "Ultra", "Thermal Protect", "Custom")
        val prefs = getSharedPreferences("crypto_launcher", MODE_PRIVATE)
        val current = prefs.getString("perf", "Balanced")
        modes.forEach { m ->
            group.addView(RadioButton(this).apply {
                text = m; setTextColor(0xFFFFFFFF.toInt()); id = m.hashCode()
                isChecked = m == current
            })
        }
        val ram = Runtime.getRuntime().maxMemory() / 1024 / 1024
        val suggest = TextView(this).apply {
            setTextColor(0xFFCCCCCC.toInt())
            text = "Device heap hint: ${ram}MB · suggest MC RAM ${ (ram / 3).coerceIn(512, 4096) }MB"
        }
        val save = Button(this).apply {
            text = "Save profile"
            setOnClickListener {
                val id = group.checkedRadioButtonId
                val rb = group.findViewById<RadioButton>(id)
                prefs.edit().putString("perf", rb?.text?.toString() ?: "Balanced").apply()
                finish()
            }
        }
        listOf(title, info, group, suggest, save).forEach { root.addView(it) }
        setContentView(root)
    }
}
