package com.example.baseapp.weather_main

import com.example.baseapp.base.BaseContract
import com.example.baseapp.network.APIClient
import com.example.baseapp.network.APIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherMainModel:WeatherMainContract.Model {

    override fun requestCurrentWeather(cityname:String,onFinishedListener: BaseContract.Model.onFinishedListener<Any>) {

        val url = "weather?q="+cityname+"&units=imperial&appid=4d2c49a5132f709423cd2dce0fdca8cb"

        CoroutineScope(Dispatchers.IO).launch {
            val call = APIClient.getClient().create(APIService::class.java).requestCurrentWeather(url)
            val body = call.body()
            if (call.isSuccessful){
                if (body != null){
                    onFinishedListener.onSuccess(body)
                }else{
                    onFinishedListener.onFailure()
                }
            }else{
                //
            }
        }
    }

    override fun requestDailyWeather(cityname:String,onFinishedListener: BaseContract.Model.onFinishedListener<Any>) {

        val url = "forecast/daily?q="+cityname+"&units=imperial&cnt=7&appid=4d2c49a5132f709423cd2dce0fdca8cb"

        CoroutineScope(Dispatchers.IO).launch {

            val call = APIClient.getClient().create(APIService::class.java).requestDailyWeather(url)
            val body = call.body()
            if (call.isSuccessful){
                if (body != null){
                    onFinishedListener.onSuccess(body)
                }else{
                    onFinishedListener.onFailure()
                }
            }else{
                //
            }
        }
    }
}