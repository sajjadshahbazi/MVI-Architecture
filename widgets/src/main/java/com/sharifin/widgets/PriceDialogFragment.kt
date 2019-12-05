package com.sharifin.widgets

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.sharifin.core.annotatePrice
import com.sharifin.core.showKeyboard
import kotlinx.android.synthetic.main.dialog_price.*


class PriceDialogFragment : DialogFragment() {

    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */

    lateinit var title: String
    lateinit var rightButton: String
    lateinit var price: String
    var canBeCancelled: Boolean = false

    interface NoticeDialogListener {
        fun onDialogConfirmButtonClick(dialog: PriceDialogFragment)
        fun onDialogCancelButtonClick(dialog: PriceDialogFragment)
    }

    companion object {
        fun newInstance(title: String, rightButton: String = "تایید", cancelable: Boolean = true, listener: NoticeDialogListener) =
                PriceDialogFragment().apply {
                    mListener = listener
                    this.title = title
                    this.rightButton = rightButton
                    this.canBeCancelled = cancelable
                }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout to use as dialog or embedded fragment
        return inflater.inflate(R.layout.dialog_price, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setStyle(STYLE_NO_FRAME, android.R.style.Theme)

        dialog?.setCancelable(canBeCancelled)

        dialog?.setOnDismissListener {
            mListener.onDialogCancelButtonClick(this)
        }

        tvPrice.annotatePrice()

        root.setOnClickListener {
            if (canBeCancelled)
                mListener.onDialogCancelButtonClick(this)
        }
        btnRight.setOnClickListener {
            price = tvPrice.text.toString()
            mListener.onDialogConfirmButtonClick(this)
        }
        tvHeader.text = title
        btnRight.text = rightButton

    }

    override fun onResume() {
        super.onResume()
        showKeyboard()
        dialog?.window?.apply {
            val params = attributes

            params.width = android.view.WindowManager.LayoutParams.MATCH_PARENT
            params.height = android.view.WindowManager.LayoutParams.MATCH_PARENT
            params.horizontalMargin = 50f

            attributes = params as android.view.WindowManager.LayoutParams
        }
    }

    /** The system calls this only when creating the layout in a dialog.  */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = object : Dialog(this.activity!!, this.theme) {
            override fun onBackPressed() {
                mListener.onDialogCancelButtonClick(this@PriceDialogFragment)
            }
        }

        // The only reason you might override this method when using onCreateView() is
        // to modify any dialog characteristics. For example, the dialog includes a
        // title by default, but your custom layout might not need it. So here you can
        // remove the dialog title, but you must call the superclass to get the Dialog.

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }


    // Use this instance of the interface to deliver action events
    lateinit var mListener: NoticeDialogListener

}