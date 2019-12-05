package com.sharifin.base.fragment_events

import io.reactivex.Observable



interface ObserveFragmentEvents{
    companion object {
            // states
            const val DISMISS_DIALOG = 0
            const val LEFT_BUTTON_CLICK = 1
            const val RIGHT_BUTTON_CLICK = 2
            const val Negative_BUTTON_CLICK = 3
            const val Positive_BUTTON_CLICK = 4
            const val BUTTON_CLICK = 5


        // Dialog Fragments
            const val ReceiptDialogFragment = 10
            const val TwoButtonDialogFragment = 11
            const val OptionsDialogFragment = 12
            const val SingleButtonDialogFragment = 13
            const val TwoButtonWithEditTextDialogFragment = 14
            const val TwoButtonCheckboxDialogFragment = 15
    }
    fun observe() : Observable<DialogEvent>
}