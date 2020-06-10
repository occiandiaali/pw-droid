package com.occian.pw_droid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_info.*

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        val bundle: Bundle? = intent.extras
        val infoLabel: String? = bundle?.getString("information")

        val actionBar = supportActionBar
        actionBar?.title = infoLabel
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val arrayAdapter: ArrayAdapter<*>
        val infoSections = arrayOf(
            "About PWDroid",
            "Help",
            "Terms of Service",
            "Cookie use",
            "Rate us",
            "Feedback",
            "Tips for passwords"
        )

        arrayAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, infoSections
        )
        infoListView.adapter = arrayAdapter

        infoListView.setOnItemClickListener { parent, view, position, id ->
            if (position == 0) {
                val aboutIntent = Intent(this@InfoActivity, AboutActivity::class.java)
                startActivity(aboutIntent)
            }
            if (position == 1) {}
            if (position == 2) {}
            if (position == 3) {}
            if (position == 4) {}
            if (position == 5) {}
            if (position == 6) {
                val pwTipsIntent = Intent(this@InfoActivity, WebViewActivity::class.java)
                pwTipsIntent.putExtra("pwinfo", "Tips for passwords")
                startActivity(pwTipsIntent)
            }
        }
    } // on create

    override fun onResume() {
        super.onResume()
        val actionBar = supportActionBar
        actionBar?.title = "Info"
       // actionBar?.setDisplayHomeAsUpEnabled(true)
    }
} // class
