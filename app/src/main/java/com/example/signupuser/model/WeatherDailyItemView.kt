package com.example.signupuser.model

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.signupuser.R
import com.example.signupuser.utils.KotlinModel

data class WeatherDailyItemView(
        val day : String,
        val tempMin: String,
        val tempMax: String,
        val urlIcon: String
) : KotlinModel(
        R.layout.view_daily_weather
) {

    private val ivWeatherW by bind<ImageView>(R.id.ivWeather)
    private val tvDayW by bind<TextView>(R.id.tvDay)
    private val tvMaxTempW by bind<TextView>(R.id.tvMaxTemp)
    private val tvMinTempW by bind<TextView>(R.id.tvMinTemp)

    override fun bindView(view: View) {
        Glide.with(ivWeatherW)
                .load(urlIcon)
                .into(ivWeatherW)
        tvDayW.text = day
        tvMaxTempW.text = tempMax
        tvMinTempW.text = tempMin
    }

}