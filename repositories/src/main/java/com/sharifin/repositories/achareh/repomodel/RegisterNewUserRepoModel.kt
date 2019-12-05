package com.sharifin.repositories.achareh.repomodel


data class RegisterNewUserRepoModel(
    var id: Int,
    val address: String,
    val lastName: String,
    val firstName: String,
    val gender: String,
    val lat: Double,
    val lng: Double,
    val coordinateMobile: String,
    val coordinatePhoneNumber: String
)