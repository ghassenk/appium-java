package com.gk.app.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.simpleButton).setOnClickListener {
            Log.i(javaClass.simpleName, "onClick()")
            findViewById<Button>(R.id.simpleTextView).text = "CLICKED"
        }
    }
}