package com.ahmedroid.ost

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder =
        WeatherViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_weather_item,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = 100

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {

    }


    inner class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}