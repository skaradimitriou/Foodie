package com.stathis.foodie.ui.webview

import android.webkit.WebSettings
import com.stathis.foodie.R
import com.stathis.foodie.abstraction.AbstractActivity
import kotlinx.android.synthetic.main.activity_webview.*


class WebviewActivity : AbstractActivity(R.layout.activity_webview) {

    override fun init() {}

    override fun running() {
        val url = intent.getStringExtra("URL")
        recipe_webview.loadUrl(url)
        val webSettings: WebSettings = recipe_webview.settings
        webSettings.javaScriptEnabled = true
    }

    override fun stopped() {}
}