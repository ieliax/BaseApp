package com.example.baseapp.weather_search

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.baseapp.R


class WeatherSearchView : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_search_view)
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        initUI()
    }

    fun initUI(){

        val editText = findViewById<EditText>(R.id.search_bar_tv) as EditText
        val cityname = intent.getStringExtra("cityname").toString()
        editText.setText(cityname)
        val pos = editText.text.length
        editText.setSelection(pos)


        val search_cta = findViewById<TextView>(R.id.search_cta)
        search_cta.setOnClickListener {
            val returnIntent = Intent()
            returnIntent.putExtra("searchvalue",editText.text.toString())
            setResult(1
                ,returnIntent)
            finish()
        }
    }

    override fun onBackPressed() {
        val returnIntent = Intent()
        setResult(0,returnIntent)
        super.onBackPressed()
    }

}