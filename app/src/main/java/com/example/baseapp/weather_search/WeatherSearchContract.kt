package com.example.baseapp.weather_search

import com.example.baseapp.base.BaseContract

interface WeatherSearchContract
{
    interface Model:BaseContract.Model{

    }

    interface View:BaseContract.View<Presenter>{

    }

    interface Presenter:BaseContract.Presenter{

    }
}