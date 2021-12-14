package com.example.baseapp.weather_daily

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.baseapp.R
import com.example.baseapp.adapters.WeatherDailyAdapter
import com.example.baseapp.dependency_factory.DependencyFactory
import com.example.baseapp.model.CurrentWeather
import com.example.baseapp.model.DailyWeather
import com.example.baseapp.model.Test
import com.example.baseapp.network.response.CurrentWeatherResponse
import java.io.Serializable

class WeatherDailyView : AppCompatActivity(),WeatherDailyContract.View {

    lateinit var weatherDailyAdaper:WeatherDailyAdapter
    lateinit var _presenter:WeatherDailyContract.Presenter

    public var elias:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_daily_view)

        //initUI()

        setPresenter(WeatherDailyPresenter(this,DependencyFactory()))
        _presenter.onViewCreated()


        //Log.d("",Intent().getSerializableExtra("class").toString())
    }

    override fun setPresenter(presenter: WeatherDailyContract.Presenter) {
        _presenter = presenter
    }

    fun initUI(){

//        weatherDailyAdaper = WeatherDailyAdapter(this)
//
//        val dailyRecyclerView = findViewById<RecyclerView>(R.id.daily_rv)
//        dailyRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
//        dailyRecyclerView.adapter = weatherDailyAdaper

    }

    override fun showDailyWeather(weatherList: ArrayList<DailyWeather>) {

        weatherDailyAdaper.weatherDailyList = weatherList
        weatherDailyAdaper.notifyDataSetChanged()
    }

    override fun onBackPressed() {
        val returnIntent = Intent()
        returnIntent.putExtra("nombre","elias")
        returnIntent.putExtra("apellido","liriano")
        returnIntent.putExtra("v",Test())
        setResult(0,returnIntent)

        finish()
        super.onBackPressed()


    }


}