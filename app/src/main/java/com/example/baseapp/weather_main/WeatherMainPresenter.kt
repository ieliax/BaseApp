package com.example.baseapp.weather_main

import com.example.baseapp.base.BaseContract
import com.example.baseapp.dependency_factory.DependencyFactory
import com.example.baseapp.dependency_factory.weatherRepositoryType
import com.example.baseapp.model.CurrentWeather
import com.example.baseapp.model.DailyWeather
import com.example.baseapp.network.response.CurrentWeatherResponse
import com.example.baseapp.network.response.DailyWeatherResponse

class WeatherMainPresenter(view:WeatherMainContract.View,dependency: DependencyFactory):WeatherMainContract.Presenter,BaseContract.Model.onFinishedListener<Any> {

    val weatherRepository:WeatherMainModel = dependency.weatherRepository(weatherRepositoryType.MAIN_MODEL_WEATHER) as WeatherMainModel
    var view:WeatherMainContract.View? = view


    override fun onViewCreated() {

    }

    override fun requestWeatherByName(cityname: String) {
        weatherRepository.requestCurrentWeather(cityname,this)
        weatherRepository.requestDailyWeather(cityname,this)
    }

    override fun onSuccess(response: Any) {

        if (response::class.java == CurrentWeatherResponse::class.java){

            val currentWeather = response as CurrentWeatherResponse
            CurrentWeather(currentWeather).let { currentWeather ->
                view?.showCurrentWeather(currentWeather)
            }

        }
        else if (response::class.java == DailyWeatherResponse::class.java){

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

    override fun onDestroy() {
        view = null
    }


}