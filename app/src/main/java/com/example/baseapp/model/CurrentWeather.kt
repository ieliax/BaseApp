package com.example.baseapp.model

import com.example.baseapp.network.response.CurrentWeatherResponse
import java.io.Serializable

class CurrentWeather(response:CurrentWeatherResponse):Serializable {

    var cityname:String? = null

    var icon_url:String? = null
    var main:String? = null

    var feels_like:Int? = null
    var temp_min:Int? = null
    var temp_max:Int? = null

    init {

        val _weather = response.weather as List<Map<String,Any>>
        val _main = response.main as Map<String,Any>
        val _icon_code = _weather[0]["icon"] as String

        cityname = response.cityname

        icon_url = "https://openweathermap.org/img/wn/"+_icon_code!!+"@2x.png"
        main = _weather[0]["main"] as String

        feels_like = (_main["feels_like"] as Double).toInt()
        temp_min = (_main["temp_min"] as Double).toInt()
        temp_max = (_main["temp_max"] as Double).toInt()


    }
}