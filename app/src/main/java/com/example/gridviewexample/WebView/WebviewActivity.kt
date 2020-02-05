package com.example.gridviewexample.WebView

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gridviewexample.R
import kotlinx.android.synthetic.main.activity_webview.*


@SuppressLint("SetJavaScriptEnabled")
class WebviewActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        browser.settings.loadsImagesAutomatically = true
        browser.settings.javaScriptEnabled = true
        browser.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY

        val url = "http://www.devglsstore.com/order-completed/23231d78-d5da-4b68-8e77-331d01804b7a?checkoutFromMobile=true&requestSource=Android&token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJ1aWQiOjM3OTI0MzUxNzYsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJwaG9uZV92ZXJpZmllZCI6ZmFsc2UsInJvbGVzIjpbIkxTX0NTVCJdLCJuYW1lIjoiWW9nZXNoIEt1c2h3YWhhIiwiZXhwIjoxNTgwODkzNjM3LCJ1c2VybmFtZSI6InlvZ2VzaC5rdXNod2FoYTJAdGVjaGdyYWlucy5jb20ifQ._9e21Jkda-M1PKFBFm-o1ODPGgciqHVwwnvREzjak56xqjzzqd-rEVWpLgajaQEx4mK9IICkMmPBlln_olCJUA"


        browser.setWebViewClient(MyBrowser())
        //  browser.loadUrl("file:///android_asset/index.html")
        open();
        //    browser.addJavascriptInterface(WebAppInterface(this), "Android")
        browser.loadUrl(url)
    }

    fun open() {
        val url: String =  field.getText().toString()
        //   browser.loadUrl("file:///android_asset/index.html")
        browser.addJavascriptInterface(object : Any() {
            @JavascriptInterface
            fun showMyOrders() {
                Log.d("LOGIN::", "Clicked")
                Toast.makeText(this@WebviewActivity, "App Click..!!", Toast.LENGTH_LONG).show()
            }
        }, "Android")
    }

    private class MyBrowser : WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView,
            url: String
        ): Boolean {
            view.loadUrl(url)
            return true
        }
    }

    /** Instantiate the interface and set the context  */
    class WebAppInterface(private val mContext: Context) {

        /** Show a toast from the web page  */
        @JavascriptInterface
        fun showMyOrders(toast: String) {
            Toast.makeText(mContext, "App Click..!!", Toast.LENGTH_SHORT).show()
        }
    }

}