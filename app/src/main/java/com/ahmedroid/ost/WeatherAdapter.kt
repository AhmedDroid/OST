package com.ahmedroid.ost

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class WeatherAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val HEADER_VIEW_TYPE: Int = 0
    private val ITEM_VIEW_TYPE: Int = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            HEADER_VIEW_TYPE -> {
                WeatherHeaderViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.layout_weather_header,
                        parent,
                        false
                    )
                )
            }
            else -> {
                WeatherItemViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.layout_weather_item,
                        parent,
                        false
                    )
                )
            }
        }


    override fun getItemCount(): Int = 100

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemViewType(position: Int): Int = when (position) {
        0 -> HEADER_VIEW_TYPE
        else -> ITEM_VIEW_TYPE
    }


    inner class WeatherHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    inner class WeatherItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}