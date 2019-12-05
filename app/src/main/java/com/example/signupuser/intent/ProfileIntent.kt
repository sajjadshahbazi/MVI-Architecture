package com.example.signupuser.intent

import com.example.signupuser.model.ProfileParamsModel
import com.sharifin.base.mvibase.MviIntent

sealed class ProfileIntent : MviIntent {
    object InitialIntent : ProfileIntent()
    object MaleClick : ProfileIntent()
    object FemaleClick : ProfileIntent()
    data class Confirm(
        val profileParamsModel : ProfileParamsModel
    ) : ProfileIntent()
}