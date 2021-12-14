package com.example.baseapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {

    const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    private var retrofit:Retrofit? = null
    const val DATA_UTL = "weather?q=santo domingo&appid=4d2c49a5132f709423cd2dce0fdca8cb"


    fun getClient():Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

}
