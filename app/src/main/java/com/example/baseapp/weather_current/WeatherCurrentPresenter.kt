package com.example.baseapp.weather_current

import android.util.Log
import com.example.baseapp.base.BaseContract
import com.example.baseapp.dependency_factory.DependencyFactory
import com.example.baseapp.dependency_factory.IDependencyFactory
import com.example.baseapp.dependency_factory.weatherRepositoryType
import com.example.baseapp.model.CurrentWeather
import com.example.baseapp.network.response.CurrentWeatherResponse
import com.example.baseapp.weather_daily.WeatherDailyContract
import com.example.baseapp.weather_daily.WeatherDailyModel

class WeatherCurrentPresenter(view:WeatherCurrentContract.View,dependency:DependencyFactory):WeatherCurrentContract.Presenter,BaseContract.Model.onFinishedListener<Any>{


    val weatherRepository:WeatherCurrentModel = dependency.weatherRepository(weatherRepositoryType.CURRENT_MODEL_WEATHER) as WeatherCurrentModel
    val weatherRepositoryD:WeatherDailyModel = dependency.weatherRepository(weatherRepositoryType.DAILY_MODEL_WEATHER) as WeatherDailyModel
    var view:WeatherCurrentContract.View? = view

    override fun onViewCreated() {
        requestCurrentWeather()

    }

    override fun requestCurrentWeather() {
        weatherRepository.requestCurrentWeather(this)
      //  weatherRepositoryD.requestDailyWeather(this)
    }

    override fun onSuccess(response: Any) {
      //  view!!.showWeatherInfo(currentWeather)

        if (response::class.java == CurrentWeatherResponse::class.java){
            Log.d("",response.toString())
            val currentWeather = response as CurrentWeatherResponse
            CurrentWeather(currentWeather).let {currentWeather ->
                view?.showCurrentWeather(currentWeather)
            }
        }else{
            Log.d("","day")
        }
    }

    override fun onFailure() {

    }

    override fun onDestroy() {
        view = null
    }

}