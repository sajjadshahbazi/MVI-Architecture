package com.example.signupuser.view

import android.content.Intent
import android.os.Bundle
import com.example.signupuser.R
import com.example.signupuser.intent.ProfileIntent
import com.example.signupuser.model.*
import com.example.signupuser.navigator.ProfileNavigator
import com.example.signupuser.state.ProfileState
import com.example.signupuser.viewmodels.ProfileViewModel
import io.reactivex.functions.Function6
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.widget.afterTextChangeEvents
import com.sharifin.base.BaseActivity
import com.sharifin.base.createViewModel
import com.sharifin.base.renderError
import com.sharifin.base.renderLoading
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import kotlinx.android.synthetic.main.activity_profile.*

const val PROFILE_ACTIVITY_TAG: String = "PROFILE_ACTIVITY_TAG"

class ProfileActivity : BaseActivity<
        ProfileIntent,
        ProfileState,
        ProfileViewModel,
        ProfileNavigator
        >() {

    lateinit var currentLocationLocalModel: CurrentLocationLocalModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        createViewModel()
        txtToolbarTitle.text = "ثبت نام"
        btnToolbarBack.setOnClickListener {
            onBackPressed()
        }
        currentLocationLocalModel = intent.extras?.getParcelable(PROFILE_ACTIVITY_TAG)
            ?: throw NullPointerException("doesn't has current location model")
    }

    override fun intents(): Observable<ProfileIntent> =
        Observable.merge(
            listOf(
                Observable.just(ProfileIntent.InitialIntent),
                confirmClickIntents(),
                rdMaleClick(),
                rdFemaleClick()
            )
        )

    private fun rdMaleClick()=
        rbMale.clicks().map {
            ProfileIntent.MaleClick
        }

    private fun rdFemaleClick()=
        rbFemale.clicks().map {
            ProfileIntent.FemaleClick
        }

    override fun render(state: ProfileState) {
        renderLoading(state.base)
        renderError(state.base)
        if (state.femaleClick){
            rbFemale.clicks()
        }
        if (state.maleClick){
            rbMale.clicks()
        }
        if(state.registerUser){
            val intent = Intent(this@ProfileActivity, ListActivity::class.java)
            startActivity(intent)
        }
    }


    private fun confirmClickIntents(): Observable<ProfileIntent.Confirm> {

        val name = edName.afterTextChangeEvents()
            .skipInitialValue()
            .filter { it.editable() != null }
            .map { it.editable()!!.toString() }

        val family = edFamily.afterTextChangeEvents()
            .skipInitialValue()
            .filter { it.editable() != null }
            .map { it.editable()!!.toString() }

        val mobile = edMobile.afterTextChangeEvents()
            .skipInitialValue()
            .filter { it.editable() != null }
            .map { it.editable()!!.toString() }

        val phone = edPhone.afterTextChangeEvents()
            .skipInitialValue()
            .filter { it.editable() != null }
            .map { it.editable()!!.toString() }

        val address = edAddress.afterTextChangeEvents()
            .skipInitialValue()
            .filter { it.editable() != null }
            .map { it.editable()!!.toString() }

        val profileParam = Observable.combineLatest(
            name,
            family,
            mobile,
            phone,
            address,
            Observable.just(currentLocationLocalModel),
            Function6<String, String, String, String, String, CurrentLocationLocalModel, ProfileParamsModel> { mName, mFamily, mMobile, mPhone, mAddress, mCurrentLocation ->
                ProfileParamsModel(
                    address = mAddress,
                    currentLocationLocalModel = mCurrentLocation,
                    coordinateMobile = mMobile,
                    coordinatePhoneNumber = mPhone,
                    firstName = mName,
                    lastName = mFamily
                )
            }
        )

        return btnConfirm.clicks()
            .withLatestFrom(profileParam,
                BiFunction<Unit, ProfileParamsModel, ProfileParamsModel>
                { _, param -> param })
            .map {
                ProfileIntent.Confirm(it.copy(gender = if (rbMale.isChecked) {
                    Male
                } else if (rbFemale.isChecked) {
                    Female
                } else {
                    NONE
                }))
            }
    }
}