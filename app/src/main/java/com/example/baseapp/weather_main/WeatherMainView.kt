package com.example.baseapp.weather_main

import android.animation.ValueAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.baseapp.R
import com.example.baseapp.adapters.WeatherDailyAdapter
import com.example.baseapp.dependency_factory.DependencyFactory
import com.example.baseapp.model.CurrentWeather
import com.example.baseapp.model.DailyWeather
import com.example.baseapp.weather_current.WeatherCurrentContract
import com.example.baseapp.weather_daily.WeatherDailyView
import com.example.baseapp.weather_search.WeatherSearchView
import com.squareup.picasso.Picasso
import kotlin.math.roundToLong

class WeatherMainView : AppCompatActivity(),WeatherMainContract.View {

    lateinit var _presenter:WeatherMainContract.Presenter

    lateinit var city: TextView
    lateinit var icon: ImageView
    lateinit var main: TextView
    lateinit var feellike: TextView
    lateinit var min: TextView
    lateinit var max: TextView
    lateinit var search_icon: ImageView

    lateinit var weatherDailyAdapter: WeatherDailyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_main_view)

        initUI()

        setPresenter(WeatherMainPresenter(this, DependencyFactory()))
        _presenter.requestWeatherByName("London")

    }

    override fun setPresenter(presenter: WeatherMainContract.Presenter) {
        _presenter = presenter
    }

    fun initUI(){

        this.search_icon = findViewById(R.id.search_iv)
        this.search_icon.setOnClickListener {
//            val intent = Intent(this, WeatherDailyView::class.java)
//            startActivityForResult(intent,0)
            val intent = Intent(this, WeatherSearchView::class.java)
            intent.putExtra("cityname",city.text.toString())
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivityForResult(intent,1)
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


        weatherDailyAdapter = WeatherDailyAdapter(this)
        val dailyRecyclerView = findViewById<RecyclerView>(R.id.daily_recycler_viewer)

        dailyRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        dailyRecyclerView.adapter = weatherDailyAdapter


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == 1){
            val cityname = data?.getStringExtra("searchvalue")!!
            city.text = cityname
            _presenter.requestWeatherByName(cityname)
        }

    }



    override fun showCurrentWeather(currentWeather: CurrentWeather) {
        runOnUiThread {

            Picasso.get().load(currentWeather.icon_url).into(this.icon)
            this.icon.alpha = 1f

            this.city.text = currentWeather.cityname
            this.main.text = currentWeather.main
            startCountAnimation(feellike,currentWeather.feels_like!!)
            startCountAnimation(min,currentWeather.temp_min!!)
            startCountAnimation(max,currentWeather.temp_max!!)

        }
    }


    override fun showDailyWeather(weatherList: ArrayList<DailyWeather>) {
        runOnUiThread {
            weatherDailyAdapter.weatherDailyList = weatherList
            weatherDailyAdapter.notifyDataSetChanged()
        }

    }

    private fun startCountAnimation(text:TextView,finalNumber:Int) {
        val animator = ValueAnimator.ofInt(0, finalNumber)
        animator.duration = 1000
        animator.startDelay = 0.5f.roundToLong()
        animator.addUpdateListener { animation -> text.setText(animation.animatedValue.toString()+"°") }
        animator.start()
    }

}