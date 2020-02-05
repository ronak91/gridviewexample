package com.example.gridviewexample


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gridviewexample.GridView.GridViewActivity
import com.example.gridviewexample.WebView.WebviewActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Grid_Layout.setOnClickListener {
            startActivity(Intent(this,
                GridViewActivity::class.java))
        }

        Webview_Layout.setOnClickListener {
            startActivity(Intent(this,
                WebviewActivity::class.java))
        }
    }

}