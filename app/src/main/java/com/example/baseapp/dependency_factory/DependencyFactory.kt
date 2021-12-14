package com.example.baseapp.dependency_factory

import com.example.baseapp.weather_current.WeatherCurrentContract
import com.example.baseapp.weather_current.WeatherCurrentModel
import com.example.baseapp.weather_daily.WeatherDailyModel
import com.example.baseapp.weather_main.WeatherMainModel


//private const val CURRENT_MODEL_WEATHER = 0
//private const val DAILY_MODEL_WEATHER = 0

enum class weatherRepositoryType{
    CURRENT_MODEL_WEATHER,
    DAILY_MODEL_WEATHER,
    MAIN_MODEL_WEATHER
}

class DependencyFactory:IDependencyFactory<weatherRepositoryType> {

    override fun weatherRepository(type:weatherRepositoryType):Any {
        when(type.ordinal){

            0 -> {return WeatherCurrentModel()}

            1 -> {return WeatherDailyModel()}

            2 -> {return WeatherMainModel()}

            else ->{return Any()
            }
        }
    }
}