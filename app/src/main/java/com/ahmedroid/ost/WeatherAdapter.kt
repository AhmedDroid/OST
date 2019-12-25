package com.ahmedroid.ost

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmedroid.data.models.WeatherDisplayData
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_weather_header.view.*
import kotlinx.android.synthetic.main.layout_weather_item.view.*

class WeatherAdapter(
    private val weatherDisplayData: WeatherDisplayData?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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


    override fun getItemCount(): Int = (weatherDisplayData?.weatherTempsHourly?.size ?: 0) + 1

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is WeatherHeaderViewHolder) {
            holder.itemView.headerCityTextView.text = weatherDisplayData?.cityName
            holder.itemView.headerWeatherStatusTextView.text = weatherDisplayData?.weatherStatus
            holder.itemView.headerWeatherTemperatureTextView.text =
                weatherDisplayData?.tempratureValue + "\u00B0"

            Glide.with(holder.itemView.context).load(weatherDisplayData?.weatherIconUrl)
                .into(holder.itemView.headerWeatherIconImageView)
        } else {
            holder.itemView.weatherHourTextView.text =
                "${getHourValue(weatherDisplayData?.weatherTempsHourly?.get(holder.adapterPosition - 1)?.time?.toInt())}:00"

            holder.itemView.weatherTempTextView.text =
                weatherDisplayData?.weatherTempsHourly?.get(holder.adapterPosition - 1)?.tempC + "\u00B0"

            Glide.with(holder.itemView.context).load(
                weatherDisplayData?.weatherTempsHourly?.get(
                    holder.adapterPosition - 1
                )?.weatherIconUrl?.get(0)?.value
            ).into(holder.itemView.weatherImageView)
        }
    }

    private fun getHourValue(hourValue: Int?): String {
        return when {
            null == hourValue -> ""
            hourValue == 0 -> "00"
            hourValue < 1000 -> "0${(hourValue / 100)}"
            else -> (hourValue / 100).toString()
        }

    }

    override fun getItemViewType(position: Int): Int = when (position) {
        0 -> HEADER_VIEW_TYPE
        else -> ITEM_VIEW_TYPE
    }

    inner class WeatherHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class WeatherItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}