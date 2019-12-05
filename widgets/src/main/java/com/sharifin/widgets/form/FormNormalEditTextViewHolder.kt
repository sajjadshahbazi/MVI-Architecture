package com.sharifin.widgets.form

import android.view.View
import com.jakewharton.rxbinding2.view.focusChanges
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.form_edit_text_review.view.*


class FormNormalEditTextViewHolder(itemView: View) : FormViewHolder(itemView) {
    lateinit var formModel: FormModel

    override fun bind(obj: FormModel) {
        this.formModel = obj
        itemView.formTitle.text = obj.title
        itemView.formValue.setText(obj.value)
        itemView.formValue.setSelection(obj.value.length)

    }

    override fun itemOnClick(item: FormModel, actionSubject: PublishSubject<FormModel>) {
        itemView.formValue.focusChanges()
                .skipInitialValue()
                .map {
                    item.copy(value = itemView.formValue.text.toString(), hasFocus = it)
                }.distinct()
                .doOnError {
                    it.printStackTrace()
                }
                .subscribe(
                        actionSubject
                )
    }
}