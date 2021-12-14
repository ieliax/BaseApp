package com.example.baseapp.weather_current

import com.example.baseapp.base.BaseContract
import com.example.baseapp.model.CurrentWeather
import com.example.baseapp.network.APIClient
import com.example.baseapp.network.APIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class WeatherCurrentModel:WeatherCurrentContract.Model {

    override fun requestCurrentWeather(onFinishedListener: BaseContract.Model.onFinishedListener<Any>) {

        val url = "weather?q=santo domingo&units=imperial&appid=4d2c49a5132f709423cd2dce0fdca8cb"

        CoroutineScope(Dispatchers.IO).launch {

            val call = APIClient.getClient().create(APIService::class.java).requestCurrentWeather(url)
            val body = call.body()

            if (call.isSuccessful){
                if (body != null){
                    onFinishedListener.onSuccess(body!!)
                }else{
                    onFinishedListener.onFailure()
                }
            }else{
                //failure
            }

        }
    }

//    override fun requestCurrentWeather(onFinishedListener: WeatherCurrentContract.Model) {
//
//        val url = "weather?q=santo domingo&appid=4d2c49a5132f709423cd2dce0fdca8cb"
//
//        CoroutineScope(Dispatchers.IO).launch {
//
//            val call = APIClient.getClient().create(APIService::class.java).requestCurrentWeather(url)
//            val body = call.body()
//
//            if (call.isSuccessful){
//                if (body != null){
//                    onFinishedListener.onSuccess(body!!)
//                }else{
//                    onFinishedListener.onFailure()
//                }
//            }else{
//                //failure
//            }
//
//        }
//    }
}