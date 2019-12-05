package com.sharifin.widgets.form

import android.view.View
import android.widget.EditText
import com.jakewharton.rxbinding2.view.focusChanges
import com.jakewharton.rxbinding2.widget.textChangeEvents
import com.sharifin.core.priceAnnotator
import com.sharifin.core.toNumber
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.form_edit_text_review.view.*

class FormPriceEditTextViewHolder(itemView: View) : FormViewHolder(itemView) {
    lateinit var formModel: FormModel

    override fun bind(obj: FormModel) {
        this.formModel = obj
        itemView.formTitle.text = obj.title
        itemView.formValue.setText(obj.value)
        if (obj.value.isNotBlank() && obj.value.length < 18)
            itemView.formValue.setSelection(obj.value.length)
        itemView.formValue.setOnEditorActionListener { view, actionId, event ->
            view.clearFocus()
            itemView.dummyFocus.requestFocus()
            true
        }

    }

    override fun itemOnClick(item: FormModel, actionSubject: PublishSubject<FormModel>) {
//        itemView.formValue.addTextChangedListener(textWatcher)
        itemView.formValue.textChangeEvents()
                .subscribeOn(Schedulers.io())
                .filter {
                    it.count() < 2 && it.before() < 2 && it.start() > 0
                }
                .map {
                    //                    it.view().removeTextChangedListener(this)
                    if (it.text().isNotBlank()) {
                        val temp: String = it.text().toString().toNumber().priceAnnotator()
                        it.view().setText(temp)
                        (it.view() as EditText).setSelection(temp.length)
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()


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