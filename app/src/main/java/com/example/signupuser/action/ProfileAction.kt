package com.example.signupuser.action

import com.example.signupuser.model.ProfileParamsModel
import com.sharifin.base.BaseAction

sealed class ProfileAction : BaseAction() {
    object InitialAction : ProfileAction()
    object MaleClick : ProfileAction()
    object FemaleClick : ProfileAction()
    data class Confirm(
        val profileParamsModel : ProfileParamsModel
    ) : ProfileAction()
}