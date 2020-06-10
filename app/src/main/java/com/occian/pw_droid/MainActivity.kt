package com.occian.pw_droid

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.ResolveInfo
import android.graphics.Color
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {

    private var radioSelection: Int = 0
    private var numsChecked: Boolean = false
    private var symsChecked: Boolean = false
    private var uppersChecked: Boolean = false
    private var lowersChecked: Boolean = false
    private var customChecked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        generateFAB.setOnClickListener {
                getAlphaNumericString(radioSelection, numsChecked, symsChecked, uppersChecked, lowersChecked)
        } // generate PW

        copyFAB.setOnClickListener {
            val textToCopy = pwResultView.text
            val clipBoard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("COPY TEXT", textToCopy)
            clipBoard.primaryClip = clip
            Toast.makeText(applicationContext, "PW copied!", Toast.LENGTH_SHORT).show()
        } // copy PW

        shareFAB.setOnClickListener {
            val mIntent = Intent(Intent.ACTION_SEND)
            mIntent.data = Uri.parse("mailto:")
            mIntent.type = "text/plain"
            mIntent.putExtra(Intent.EXTRA_TEXT, pwResultView.text)

            try {
                startActivity(Intent.createChooser(mIntent, "Send via"))
            } catch (e: Exception) {
                Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
            }
        } // share PW
    } // on create

    // radio button action
    fun onRadioButtonClick(view: View): Int {
        if (view is RadioButton) {
            val checked = view.isChecked
            when (view.getId()) {
                R.id.radioView16 ->
                    if (checked) {
                        radioSelection = 16
                    }
                R.id.radioView32 ->
                    if (checked) {
                        radioSelection = 32
                    }
                R.id.radioView64 ->
                    if (checked) {
                        radioSelection = 64
                    }
            }
        }
        return radioSelection
    } // on radio button click

    // check box action
    fun onCheckBoxClick(view: View): Boolean {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked
            when (view.id) {
                R.id.numsCheckBox -> {
                    numsChecked = checked
                    return numsChecked
                }
                R.id.symsCheckBox -> {
                    symsChecked = checked
                    return symsChecked
                }
                R.id.uppersCheckBox -> {
                    uppersChecked = checked
                    return uppersChecked
                }
                R.id.lowersCheckBox -> {
                    lowersChecked = checked
                    return lowersChecked
                }
                R.id.customsCheckBox -> {
                    // toggle enable/disable of other checkboxes
                    if (!customChecked) {
                        customChecked = checked
                        customEditText.isEnabled = true
                        Toast.makeText(
                            applicationContext,
                            "Edit field now enabled",
                            Toast.LENGTH_SHORT
                        ).show()
                        numsChecked = false
                        numsCheckBox.isEnabled = false
                        symsChecked = false
                        symsCheckBox.isEnabled = false
                        uppersChecked = false
                        uppersCheckBox.isEnabled = false
                        lowersChecked = false
                        lowersCheckBox.isEnabled = false
                    } else {
                        customChecked = false
                        customEditText.text.clear()
                        customEditText.isEnabled = false
                        pwResultView.text = ""
                        pwLevelView.text = ""
                        Toast.makeText(
                            applicationContext,
                            "Edit field is disabled",
                            Toast.LENGTH_SHORT
                        ).show()
                        numsChecked = true
                        numsCheckBox.isEnabled = true
                        symsChecked = true
                        symsCheckBox.isEnabled = true
                        uppersChecked = true
                        uppersCheckBox.isEnabled = true
                        lowersChecked = true
                        lowersCheckBox.isEnabled = true
                    }
                }
            }
        }
        return false
    } // on check box click

//    private fun customPW() {
//        userInput = customEditText.text.toString()
//    }

   // @RequiresApi(Build.VERSION_CODES.N)
    private fun getAlphaNumericString(n: Int, numsCheck: Boolean, symsCheck: Boolean, uppersCheck: Boolean, lowersCheck: Boolean
    ): String {
        var baseStr = ""
        if (numsCheck) baseStr += "0123456789"
        if (symsCheck) baseStr += "@!#$%&*?^"
        if (uppersCheck) baseStr += "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        if (lowersCheck) baseStr += "abcdefghijklmnopqrstuvwxyz"
        if (customChecked) baseStr += "${customEditText.text}QUXZ~<>/89"

       if (customChecked && customEditText.text.isEmpty()) {
           Toast.makeText(applicationContext, "This is auto-generated. Type a phrase or sentence for your custom PW", Toast.LENGTH_SHORT).show()
       }

        val alphaNumericString = baseStr
        val sb = StringBuilder(n)

        if (n == 16) {
            if (pwResultView.text.isNotEmpty()) {
                pwResultView.textSize = 20F
                pwLevelView.text = "Okay"
                pwLevelView.setTextColor(Color.parseColor("#ddba22"))
            }
        }
        if (n == 32) {
            if (pwResultView.text.isNotEmpty()) {
                pwResultView.textSize = 20F
                pwLevelView.text = "Good"
                pwLevelView.setTextColor(Color.parseColor("#a3ad0b"))
            }
        }
        if (n == 64) {
            if (pwResultView.text.isNotEmpty()) {
                pwResultView.textSize = 17F
                pwResultView.textScaleX = 1.5F
                pwLevelView.text = "Strong"
                pwLevelView.setTextColor(Color.parseColor("#21de7c"))
            }
        }


        if (n > 0) {
            for (i in 0..n) {
                var index = (alphaNumericString.length * Math.random()).toInt()
                try {
                    sb.append(alphaNumericString[index])
                } catch (e: Exception) {
                    pwLevelView.text = "Select at least one of the checkboxes!"
                    pwLevelView.setTextColor(Color.RED)
                }
            }
        } else {
            pwLevelView.text = "Choose a PW Length!"
            pwLevelView.setTextColor(Color.RED)
        }
        pwResultView.text = sb.toString()
        return pwResultView.toString()
    } // get alphanumeric string function




    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.settings -> {
                val settingsIntent = Intent(this@MainActivity, SettingsActivity::class.java)
                settingsIntent.putExtra("label", "Settings")
                startActivity(settingsIntent)
                true
            }
            R.id.info -> {
                val infoIntent = Intent(this@MainActivity, InfoActivity::class.java)
                infoIntent.putExtra("information", "Info")
                startActivity(infoIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
} // class
