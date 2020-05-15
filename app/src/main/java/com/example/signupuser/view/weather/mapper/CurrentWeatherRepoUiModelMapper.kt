package com.example.signupuser.view.weather.mapper

import com.example.signupuser.R
import com.example.signupuser.view.weather.models.CurrentWeatherUiModel
import com.sharifin.core.Mapper
import com.sharifin.repositories.weather.repomodel.CurrentWeatherRepoModel
import java.util.*
import javax.inject.Inject

class CurrentWeatherRepoUiModelMapper @Inject constructor() : Mapper<
        CurrentWeatherRepoModel,
        CurrentWeatherUiModel
        > {
    override fun map(item: CurrentWeatherRepoModel): CurrentWeatherUiModel =
            CurrentWeatherUiModel(
                background = dateTimeMapToBackground(
                        item.dt
                ),
                imgToday = descriptionMapToWeatherImg(
                        item.weather.first().description
                ),
                currentTemp = "${(item.main.temp - 273.15)} C",
                rangeTemp ="${
                (item.main.tempMin - 273.15)
                } C / ${
                (item.main.tempMax - 273.15)
                } C"
        )

    private fun descriptionMapToWeatherImg(desc : String) =
        when(desc){
            "broken cloud" -> R.drawable.ic_broken_cloud
            "clear sky dawn" -> R.drawable.ic_clear_sky_dawn
            "clear sky evening" -> R.drawable.ic_clear_sky_evening
            "clear sky morning" -> R.drawable.ic_clear_sky_morning
            "clear sky night" -> R.drawable.ic_clear_sky_night
            "few cloud dawn" -> R.drawable.ic_few_cloud_dawn
            "few cloud evening" -> R.drawable.ic_few_cloud_evening
            "few cloud morning" -> R.drawable.ic_few_cloud_morning
            "few cloud night" -> R.drawable.ic_few_clound_night
            "mist" -> R.drawable.ic_mist
            "rain" -> R.drawable.ic_rain
            "scattered clouds" -> R.drawable.ic_scattered_clouds
            "shower raint" -> R.drawable.ic_shower_raint
            "snow" -> R.drawable.ic_snow
            "thunderstorm" -> R.drawable.ic_thunderstorm
            else -> R.drawable.ic_clear_sky_dawn
    }

    private fun dateTimeMapToBackground(dt : Long) : Int{
        val calendar = Calendar.getInstance(Locale.ENGLISH)
        calendar.timeInMillis = dt * 1000L
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        return when(hour){
            in 0..6 ->{ R.drawable.bg_dawn }
            in 6..12 ->{R.drawable.bg_morning}
            in 12..16 ->{R.drawable.bg_noon}
            in 16..21 ->{R.drawable.bg_evening}
            in 21..24 ->{R.drawable.bg_night}
            else -> R.drawable.bg_noon
        }
    }

}