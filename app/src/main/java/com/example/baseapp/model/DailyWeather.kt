package com.example.baseapp.model

class DailyWeather(response:Map<String,Any>)  {

    var feels_like:Double? = null
    var description:String? = null
    var icon_code:String? = null

    init {

        val feelslike = response["feels_like"] as Map<String,Any>
        feels_like = feelslike["day"] as Double

        val weather = response["weather"] as List<Map<String,Any>>
        description = weather[0]["description"] as String
        icon_code = weather[0]["icon"] as String

    }

}