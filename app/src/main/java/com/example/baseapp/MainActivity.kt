package com.example.baseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.baseapp.dependency_factory.DependencyFactory
import com.example.baseapp.model.CurrentWeather
import com.example.baseapp.weather_current.WeatherCurrentContract
import com.example.baseapp.weather_current.WeatherCurrentPresenter
import com.example.baseapp.weather_current.WeatherCurrentView
import com.example.baseapp.weather_daily.WeatherDailyView


class MainActivity : AppCompatActivity(){
//
//    lateinit var cityname:TextView
//    lateinit var currenweather:TextView
//    lateinit var weatherdescription:TextView

    lateinit var current_btn:Button
    lateinit var daily_btn:Button

    lateinit var _presenter:WeatherCurrentContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()

  //      setPresenter(WeatherCurrentPresenter(this,DependencyFactory()))
    //    _presenter.onViewCreated()

    }

//    override fun setPresenter(presenter: WeatherCurrentContract.Presenter) {
//        _presenter = presenter
//    }

    fun initUI(){
//        this.cityname = findViewById(R.id.city_name_tv)
//
//        this.currenweather = findViewById(R.id.weather_current_tv)
//        this.weatherdescription = findViewById(R.id.weather_description_tv)

        this.current_btn = findViewById(R.id.current_btn)
        this.daily_btn = findViewById(R.id.daily_btn)

        this.current_btn.setOnClickListener {
            val intent = Intent(this,WeatherCurrentView::class.java)
//            startActivity(intent)
            startActivityForResult(intent,0)
        }

        this.daily_btn.setOnClickListener {
            val intent = Intent(this,WeatherDailyView::class.java)
            startActivity(intent)
//            startActivityForResult(intent,0)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        Log.d("",requestCode.toString())
//        Log.d("",data?.getStringExtra("nombre")!!)
        Log.d("",data?.getSerializableExtra("c")!!.toString())
    }

//    override fun onDestroy() {
//        _presenter.onDestroy()
//        super.onDestroy()
//    }
//
//    override fun showWeatherInfo(currentWeather: CurrentWeather) {
//
//        runOnUiThread {
//         //   this.cityname.text = currentWeather.cityname
//            Log.d("TAGxxx111", currentWeather.toString())
//            for (item in currentWeather.weather){
//
//            }
////            this.currenweather.text = (currentWeather.weather[0] as Map<String,Any>)["main"].toString()
////            this.weatherdescription.text = (currentWeather.weather[0] as Map<String,Any>)["description"].toString()
//        }
//
//    }



//    override fun setPresenter(v: WeatherCurrentContract.Presenter) {
//
//    }

}