package com.example.baseapp.network

import com.example.baseapp.model.CurrentWeather
import com.example.baseapp.network.response.CurrentWeatherResponse
import com.example.baseapp.network.response.DailyWeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun requestCurrentWeather(@Url url:String):Response<CurrentWeatherResponse>
    @GET
    suspend fun requestDailyWeather(@Url url:String):Response<DailyWeatherResponse>
}