package com.occian.pw_droid

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_themes.*

class ThemesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_themes)

        val bundle: Bundle? = intent.extras
        val screenLabel: String? = bundle?.getString("key")

        val actionBar = supportActionBar
        actionBar?.title = screenLabel
       actionBar?.setDisplayHomeAsUpEnabled(true)

        val arrayAdapter: ArrayAdapter<*>
        val sections = arrayOf(
            "Display Light",
            "Display Dark",
            "Wallpaper",
            "Skins"
        )

        arrayAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, sections
        )
        themesListView.adapter = arrayAdapter

        themesListView.setOnItemClickListener { parent, view, position, id ->
            if (position == 0) {
                //Toast.makeText(applicationContext, "Light Mode on", Toast.LENGTH_SHORT).show()
                themesListView.setBackgroundColor(Color.WHITE)
            }
            if (position == 1) {
                themesListView.setBackgroundColor(Color.DKGRAY)
                //view.setBackgroundColor(Color.WHITE)
            }
            if (position == 2) {
                val paperIntent = Intent(Intent.ACTION_SET_WALLPAPER)
                startActivity(Intent.createChooser(paperIntent, "Select wallpaper"))
            }
            if (position == 3) {
                Toast.makeText(applicationContext, "Get Help", Toast.LENGTH_SHORT).show()
            }

        }
    } // on create
} // class
