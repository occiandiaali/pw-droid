package com.occian.pw_droid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val actionBar = supportActionBar
        actionBar?.title = "Settings"
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val arrayAdapter: ArrayAdapter<*>
        val settingsSections = arrayOf(
            "Themes",
            "Notifications",
            "Data and storage usage",
            "Accessibility"
        )

        arrayAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, settingsSections
        )
        settingsListView.adapter = arrayAdapter

        settingsListView.setOnItemClickListener { parent, view, position, id ->
            if (position == 0) {
                val themesIntent = Intent(applicationContext, ThemesActivity::class.java)
                themesIntent.putExtra("key", "Themes")
                startActivity(themesIntent)
            }
            if (position == 1) {
                val themesIntent = Intent(applicationContext, ThemesActivity::class.java)
                themesIntent.putExtra("key", "Themes")
                startActivity(themesIntent)
            }
            if (position == 2) {
                val themesIntent = Intent(applicationContext, ThemesActivity::class.java)
                themesIntent.putExtra("key", "Themes")
                startActivity(themesIntent)
            }
            if (position == 3) {
                val themesIntent = Intent(applicationContext, ThemesActivity::class.java)
                themesIntent.putExtra("key", "Themes")
                startActivity(themesIntent)
            }
            if (position == 4) {
                val themesIntent = Intent(applicationContext, ThemesActivity::class.java)
                themesIntent.putExtra("key", "Themes")
                startActivity(themesIntent)
            }
        }
    } // on create

    override fun onResume() {
        super.onResume()
        val actionBar = supportActionBar
        actionBar?.title = "Settings"
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
} // class
