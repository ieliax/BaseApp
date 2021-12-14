package com.example.baseapp.weather_daily

import com.example.baseapp.base.BaseContract
import com.example.baseapp.network.APIClient
import com.example.baseapp.network.APIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherDailyModel:WeatherDailyContract.Model {
    override fun requestDailyWeather(onFinishedListener: BaseContract.Model.onFinishedListener<Any>) {

        val url = "forecast/daily?q=London&units=imperial&cnt=7&appid=4d2c49a5132f709423cd2dce0fdca8cb"

       CoroutineScope(Dispatchers.Main).launch {

           val call = APIClient.getClient().create(APIService::class.java).requestDailyWeather(url)
           val body = call.body()!!

           if (call.isSuccessful){

               if (body != null){
                   onFinishedListener.onSuccess(body)
               }else{
                   onFinishedListener.onFailure()
               }

           }else{
               //fail
           }

       }
    }
}