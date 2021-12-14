package com.example.baseapp.network.response

import com.google.gson.annotations.SerializedName

data class DailyWeatherResponse(
    @SerializedName(value = "cod") var cod:String,
    @SerializedName(value = "message") var message:String,
    @SerializedName(value = "ctn") var ctn:String,
    @SerializedName(value = "list") var dailyList:List<Any>) {

}