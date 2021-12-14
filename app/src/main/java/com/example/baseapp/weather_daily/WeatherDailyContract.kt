package com.example.baseapp.weather_daily

import com.example.baseapp.base.BaseContract
import com.example.baseapp.model.DailyWeather

interface WeatherDailyContract {
    interface Model{
//        interface onFinishedListener{
//            fun onSuccess(v:Any)
//            fun onFailure()
//        }
        fun requestDailyWeather(onFinishedListener:BaseContract.Model.onFinishedListener<Any>)
    }

    interface View:BaseContract.View<Presenter>{
        fun showDailyWeather(weatherList:ArrayList<DailyWeather>)
    }

    interface Presenter:BaseContract.Presenter{
        fun requestDailyWeather()
    }
}