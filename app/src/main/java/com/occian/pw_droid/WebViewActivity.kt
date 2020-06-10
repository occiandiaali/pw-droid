package com.occian.pw_droid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web_view.*
import java.util.*

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val siteUrls = arrayOf(
            "https://www.webroot.com/us/en/resources/tips-articles/how-do-i-create-a-strong-password",
            "https://blog.avast.com/strong-password-ideas",
            "https://www.mentalfloss.com/article/504786/8-tips-make-your-passwords-strong-possible",
            "https://www.mcafee.com/blogs/consumer/family-safety/15-tips-to-better-password-security/",
            "https://us.norton.com/internetsecurity-how-to-how-to-secure-your-passwords.html",
            "https://www.wired.com/story/7-steps-to-password-perfection/"
        )

        val bundle: Bundle? = intent.extras
        val pwTipsLabel: String? = bundle?.getString("pwinfo")

        val actionBar = supportActionBar
        actionBar?.title = pwTipsLabel
        //actionBar?.setDisplayHomeAsUpEnabled(true)

        webView.webViewClient = WebViewClient()
        webView.settings.setSupportZoom(true)
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(getRandomUrl(siteUrls))
    } // on create

    fun getRandomUrl(sUrls: Array<String>): String {
        val rand = Random()
        return sUrls.get(rand.nextInt(sUrls.size))
    }
} // class
