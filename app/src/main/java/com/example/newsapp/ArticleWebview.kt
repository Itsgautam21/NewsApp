package com.example.newsapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class ArticleWebview : AppCompatActivity() {
    var webView: WebView? = null
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_webview)
        webView = findViewById(R.id.webVIew)
        webView!!.settings.javaScriptEnabled = true
        webView!!.webViewClient = WebViewClient()
        webView!!.loadUrl(intent.getStringExtra("urls")!!)
    }
}