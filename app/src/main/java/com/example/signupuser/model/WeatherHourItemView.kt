package com.example.signupuser.model

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.signupuser.R
import com.example.signupuser.utils.KotlinModel

data class WeatherHourItemView(
        val time: String,
        val temp: String,
        val urlIcon: String
) : KotlinModel(
        R.layout.view_hourly_weather
) {

    private val ivHourlyW by bind<ImageView>(R.id.ivHourly)
    private val tvTempW by bind<TextView>(R.id.tvTemp)
    private val tvHourW by bind<TextView>(R.id.tvHour)

    override fun bindView(view: View) {
        Glide.with(ivHourlyW)
                .load(urlIcon)
                .into(ivHourlyW)
        tvTempW.text = temp
        tvHourW.text = time
    }
}