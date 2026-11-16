package ru.cryptolauncher

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject

class ServersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(0xFF0A0E16.toInt())
            setPadding(32, 48, 32, 32)
        }
        val title = TextView(this).apply {
            text = "Servers · Quick Join"
            setTextColor(0xFF6FA8FF.toInt())
            textSize = 20f
        }
        val name = EditText(this).apply { hint = "Name"; setTextColor(0xFFFFFFFF.toInt()); setHintTextColor(0xFF888888.toInt()) }
        val ip = EditText(this).apply { hint = "IP"; setTextColor(0xFFFFFFFF.toInt()); setHintTextColor(0xFF888888.toInt()) }
        val port = EditText(this).apply { hint = "Port"; setText("25565"); setTextColor(0xFFFFFFFF.toInt()); setHintTextColor(0xFF888888.toInt()) }
        val list = TextView(this).apply { setTextColor(0xFFCCCCCC.toInt()); textSize = 14f }
        val prefs = getSharedPreferences("crypto_launcher", MODE_PRIVATE)
        fun reload() {
            val arr = JSONArray(prefs.getString("servers", "[]"))
            val sb = StringBuilder()
            for (i in 0 until arr.length()) {
                val o = arr.getJSONObject(i)
                sb.append("${o.getString("name")} — ${o.getString("ip")}:${o.getInt("port")}\n")
            }
            list.text = if (sb.isEmpty()) "No servers" else sb.toString()
        }
        reload()
        val add = Button(this).apply {
            text = "Save server"
            setOnClickListener {
                val arr = JSONArray(prefs.getString("servers", "[]"))
                arr.put(JSONObject()
                    .put("name", name.text.toString().ifBlank { "Server" })
                    .put("ip", ip.text.toString())
                    .put("port", port.text.toString().toIntOrNull() ?: 25565))
                prefs.edit().putString("servers", arr.toString()).apply()
                reload()
                Toast.makeText(this@ServersActivity, "Saved", Toast.LENGTH_SHORT).show()
            }
        }
        val join = Button(this).apply {
            text = "Quick Join (needs runtime)"
            setOnClickListener {
                Toast.makeText(this@ServersActivity, "Will pass --server IP:port when runtime is integrated", Toast.LENGTH_LONG).show()
            }
        }
        val back = Button(this).apply { text = "Back"; setOnClickListener { finish() } }
        listOf(title, name, ip, port, add, join, list, back).forEach { root.addView(it) }
        setContentView(root)
    }
}
