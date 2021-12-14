package com.example.baseapp.weather_current

import android.animation.ValueAnimator
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.baseapp.R
import com.example.baseapp.dependency_factory.DependencyFactory
import com.example.baseapp.model.CurrentWeather
import com.example.baseapp.weather_daily.WeatherDailyView
import com.squareup.picasso.Picasso
import kotlin.math.roundToLong


class WeatherCurrentView : AppCompatActivity(),WeatherCurrentContract.View {

    lateinit var _presenter:WeatherCurrentContract.Presenter

    lateinit var city:TextView
    lateinit var icon:ImageView
    lateinit var main:TextView
    lateinit var feellike:TextView
    lateinit var min:TextView
    lateinit var max:TextView
    lateinit var search_icon:ImageView

    lateinit var _current:CurrentWeather

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_current_view)

        initUI()

        setPresenter(WeatherCurrentPresenter(this,DependencyFactory()))
        _presenter.onViewCreated()
    }

    fun initUI(){

        this.search_icon = findViewById(R.id.search_iv)
        this.search_icon.setOnClickListener {

            val intent = Intent(this,WeatherDailyView::class.java)
            startActivityForResult(intent,0)
        }

        this.city = findViewById(R.id.city_tv)
        this.city.text = "Loading..."

        this.icon = findViewById(R.id.icon_iv)
        this.icon.alpha = 0f

        this.main = findViewById(R.id.main_tv)
        this.main.text = ""

        this.feellike = findViewById(R.id.feellike_tv)
        this.feellike.text = "0°"

        this.min = findViewById(R.id.mix_tv)
        this.min.text = "0°"

        this.max = findViewById(R.id.max_tv)
        this.max.text = "0°"


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 0 && resultCode == Activity.RESULT_OK){

        }
//        Log.d("",requestCode.toString())
//        Log.d("",data?.getStringExtra("nombre")!!)
//        Log.d("",data?.getSerializableExtra("v")!!.toString())
    }

    override fun setPresenter(presenter: WeatherCurrentContract.Presenter) {
      _presenter = presenter
    }

    override fun showCurrentWeather(currentWeather: CurrentWeather) {

        this._current = currentWeather
        runOnUiThread {

            Picasso.get().load(currentWeather.icon_url).into(this.icon)
            this.icon.alpha = 1f

            this.city.text = currentWeather.cityname
            this.main.text = currentWeather.main
            startCountAnimation(feellike,currentWeather.feels_like!!)
            startCountAnimation(min,currentWeather.temp_min!!)
            startCountAnimation(max,currentWeather.temp_max!!)

//            this.main.text = currentWeather.main
//            this.feellike.text = currentWeather.feels_like.toString()
//            this.maxmix.text =  currentWeather.temp_min.toString()+"° / "+currentWeather.temp_max.toString()+"°"

        }
    }

    private fun startCountAnimation(text:TextView,finalNumber:Int) {
        val animator = ValueAnimator.ofInt(0, finalNumber)
        animator.duration = 1000
        animator.startDelay = 0.5f.roundToLong()
        animator.addUpdateListener { animation -> text.setText(animation.animatedValue.toString()+"°") }
        animator.start()
    }

    override fun onBackPressed() {
        val returnIntent = Intent()
        returnIntent.putExtra("c",this._current)
        setResult(0,returnIntent)
        super.onBackPressed()
    }
}