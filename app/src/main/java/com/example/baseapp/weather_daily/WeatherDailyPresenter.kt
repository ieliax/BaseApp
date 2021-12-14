package com.example.baseapp.weather_daily

import android.util.Log
import com.example.baseapp.base.BaseContract
import com.example.baseapp.dependency_factory.DependencyFactory
import com.example.baseapp.dependency_factory.IDependencyFactory
import com.example.baseapp.dependency_factory.weatherRepositoryType
import com.example.baseapp.model.DailyWeather
import com.example.baseapp.network.response.CurrentWeatherResponse
import com.example.baseapp.network.response.DailyWeatherResponse

class WeatherDailyPresenter(view:WeatherDailyContract.View,dependency:DependencyFactory):WeatherDailyContract.Presenter,
    BaseContract.Model.onFinishedListener<Any> {

    val weatherRepository:WeatherDailyModel = dependency.weatherRepository(weatherRepositoryType.DAILY_MODEL_WEATHER) as WeatherDailyModel
    var view:WeatherDailyContract.View? = view

    override fun onViewCreated() {
       requestDailyWeather()
    }

    override fun requestDailyWeather() {
        weatherRepository.requestDailyWeather(this)
    }

    override fun onDestroy() {
        view = null
    }

    override fun onSuccess(response: Any) {
        if (response::class.java == CurrentWeatherResponse::class.java){
            Log.d("","current daily")
        }else{

            val dailyResponse = response as DailyWeatherResponse
            val dailyWeatherList:ArrayList<DailyWeather> = ArrayList()

            for (snapshop in dailyResponse.dailyList){

                val snap = snapshop as Map<String,Any>

                DailyWeather(snap).let { dailyWeather ->
                    dailyWeatherList.add(dailyWeather)
                }
            }

            view?.showDailyWeather(dailyWeatherList)

        }
    }





    override fun onFailure() {

    }
}