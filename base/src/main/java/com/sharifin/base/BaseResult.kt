package com.sharifin.base

import com.sharifin.base.mvibase.MviResult

open class BaseResult : MviResult {
    data class Initial(val initData: Any? = null) : BaseResult()
    data class Error(val errorMessage: String = "", val errorID: Int) : BaseResult()
    object Loading : BaseResult()
    object LastStable : BaseResult()
    sealed class ButtonClicked {
        object DestinationCards : ButtonClicked()
        object SourceCards : ButtonClicked()
        object Confirm : ButtonClicked()
        object ScanCard : ButtonClicked()
        object CoveredBanks : ButtonClicked()
    }
}