package com.alfanshter.suhurealtime

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_diagram.*

class Diagram : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diagram)

        kelembabantp.settings.javaScriptEnabled = true
        kelembabantp.loadUrl(Uri.parse("file:///android_asset/index.html").toString())

    }
}
