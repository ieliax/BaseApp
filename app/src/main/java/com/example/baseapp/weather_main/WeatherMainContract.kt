package com.example.baseapp.weather_main

import com.example.baseapp.base.BaseContract
import com.example.baseapp.model.CurrentWeather
import com.example.baseapp.model.DailyWeather

interface WeatherMainContract {

    interface Model:BaseContract.Model{
        fun requestCurrentWeather(cityname: String,onFinishedListener: BaseContract.Model.onFinishedListener<Any>)
        fun requestDailyWeather(cityname: String,onFinishedListener: BaseContract.Model.onFinishedListener<Any>)
    }

    interface View:BaseContract.View<Presenter>{
        fun showCurrentWeather(currentWeather: CurrentWeather)
        fun showDailyWeather(weatherList:ArrayList<DailyWeather>)
    }

    interface Presenter:BaseContract.Presenter{
        fun requestWeatherByName(cityname:String)
    }

}