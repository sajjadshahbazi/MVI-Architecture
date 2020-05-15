package com.example.signupuser.view.weather

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.signupuser.R
import com.example.signupuser.intent.WeatherIntent
import com.example.signupuser.model.WeatherDailyItemView
import com.example.signupuser.model.WeatherHourItemView
import com.example.signupuser.navigator.WeatherNavigator
import com.example.signupuser.state.WeatherState
import com.example.signupuser.viewmodels.WeatherViewModel
import com.sharifin.base.BaseActivity
import com.sharifin.base.createViewModel
import com.sharifin.base.renderError
import com.sharifin.base.renderLoading
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_weather.*

class WeatherActivity : BaseActivity<
        WeatherIntent,
        WeatherState,
        WeatherViewModel,
        WeatherNavigator
        >() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        createViewModel()

        rcHourly.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
        )

        rcDaily.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
        )
    }

    override fun intents(): Observable<WeatherIntent> =
            Observable.merge(
                    listOf(
                            Observable.just(WeatherIntent.Initial)
                    )
            )

    override fun render(state: WeatherState) {
        renderLoading(state.base)
        renderError(state.base)

        root.takeIf {
            state.currentUIWeather != null
        }?.apply {
            visibility = View.VISIBLE
            background = ContextCompat.getDrawable(
                    this@WeatherActivity,
                    state.currentUIWeather!!.background
            )
        }

        tvCurrentTemp.takeIf {
            state.currentUIWeather != null
        }?.apply {
            text = state.currentUIWeather!!.currentTemp
        }

        tvCurrentCity.apply {
            text = state.city
        }

        tvRangeTemp.takeIf {
            state.currentUIWeather != null
        }?.apply {
            text = state.currentUIWeather!!.rangeTemp
        }

        ivShowToday.takeIf {
            state.currentUIWeather != null
        }?.apply {
            setImageDrawable(ContextCompat.getDrawable(
                    this@WeatherActivity,
                    state.currentUIWeather!!.imgToday
            ))
        }


        rcHourly.withModels {
            state.hourlyUiModel.forEachIndexed { index, hourlyUiModel ->
                WeatherHourItemView(
                        time = hourlyUiModel.time,
                        temp = hourlyUiModel.temp,
                        urlIcon = hourlyUiModel.iconUrl
                ).id(index)
                        .addTo(this)

            }
        }

        rcDaily.withModels {

            state.dailyUiModel.forEachIndexed { index, dailyUiModel ->
                WeatherDailyItemView(
                        day = positionToDay(index),
                        tempMin = dailyUiModel.minTemp,
                        tempMax = dailyUiModel.maxTemp,
                        urlIcon = dailyUiModel.iconUrl
                ).id(index)
                        .addTo(this)
            }
        }

    }

    fun positionToDay(pos: Int): String {
        return when (pos) {
            0 -> "Monday"
            1 -> "Tuesday"
            2 -> "Wednesday"
            3 -> "Thursday"
            4 -> "Friday"
            5 -> "Saturday"
            6 -> "Sunday"
            else -> ""
        }
    }

}