package com.example.signupuser.model

import android.view.View
import android.widget.TextView
import com.example.signupuser.R
import com.example.signupuser.utils.KotlinModel

data class UserEpoxyModel(
    val address: String = "",
    val lastName: String = "",
    val firstName: String =""
) : KotlinModel(
    R.layout.row_user
) {
    private val mFirstName by bind<TextView>(R.id.firstName)
    private val mLastName by bind<TextView>(R.id.lastName)
    private val tvAddress by bind<TextView>(R.id.tvAddress)
    override fun bindView(view: View) {
        mFirstName.text = firstName
        mLastName.text = lastName
        tvAddress.text = address

    }

}