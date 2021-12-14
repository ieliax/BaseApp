package com.example.baseapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.baseapp.R
import com.example.baseapp.model.DailyWeather
import com.example.baseapp.weather_main.WeatherMainView
import com.squareup.picasso.Picasso


class WeatherDailyAdapter(context:WeatherMainView):RecyclerView.Adapter<WeatherDailyAdapter.WeatherDailyHolder>() {

    val _context = context
    var weatherDailyList:ArrayList<DailyWeather>? = ArrayList()

    class WeatherDailyHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var icon_iv = itemView.findViewById<ImageView>(R.id.icon_iv)
        var feels_like = itemView.findViewById<TextView>(R.id.title_tv)
        var description = itemView.findViewById<TextView>(R.id.details_tv)
        init {}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherDailyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_weather_daily,parent,false)
        view.layoutParams.height = (_context.resources.displayMetrics.heightPixels-44) / 7
        return WeatherDailyHolder(view)
    }

    override fun getItemCount(): Int {
        return weatherDailyList!!.size
    }

    override fun onBindViewHolder(holder: WeatherDailyHolder, position: Int) {

        val weatherDaily = weatherDailyList!![position]

        holder.feels_like.text = weatherDaily.feels_like!!.toUInt().toString()+"°F"
        holder.description.text = weatherDaily.description

        val weatherIconUrl = "https://openweathermap.org/img/wn/"+weatherDaily.icon_code!!+"@2x.png"

        Picasso.get()
            .load(weatherIconUrl)
            .into(holder.icon_iv)

        Log.d("",weatherIconUrl.toString())
    }

}