package com.example.signupuser.model

const val Male : String = "MALE"
const val Female : String = "Female"
const val NONE : String = "NONE"

data class ProfileParamsModel(
    val region : Int = 1,
    val address : String = "",
    val currentLocationLocalModel : CurrentLocationLocalModel,
    val coordinateMobile : String = "",
    val coordinatePhoneNumber : String = "",
    val firstName : String = "",
    val lastName : String = "",
    val gender : String = NONE
)