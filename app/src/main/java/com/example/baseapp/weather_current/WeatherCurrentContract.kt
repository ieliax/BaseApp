package com.example.baseapp.weather_current

import com.example.baseapp.base.BaseContract
import com.example.baseapp.model.CurrentWeather
import com.example.baseapp.network.response.CurrentWeatherResponse

interface WeatherCurrentContract {

    interface Model:BaseContract.Model{
//        interface onFinishedListener{
//            fun onSuccess(currentWeather:Any)
//            fun onFailure()
//        }
        fun requestCurrentWeather(onFinishedListener:BaseContract.Model.onFinishedListener<Any>)
    }
//
    interface View:BaseContract.View<Presenter>{
        fun showCurrentWeather(currentWeather:CurrentWeather)
    }
//
    interface Presenter:BaseContract.Presenter{
        fun requestCurrentWeather()
    }

}