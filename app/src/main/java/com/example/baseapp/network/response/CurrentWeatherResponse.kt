package com.example.baseapp.network.response

import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    @SerializedName(value = "cod") var code:String,
    @SerializedName(value = "name") var cityname:String,
    @SerializedName(value = "coord") var coordinate:Map<String,Any>,
    @SerializedName(value = "weather") var weather:List<Any>,
    @SerializedName(value = "main") var main:Map<String,Any>) {

}