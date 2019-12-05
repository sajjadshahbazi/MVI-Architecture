package com.sharifin.widgets.form

import android.view.View
import com.jakewharton.rxbinding2.view.clicks
import com.sharifin.core.priceAnnotator
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.form_text_review.view.*

class FormTextViewPriceHolder(itemView: View) : FormViewHolder(itemView){

    override fun bind(obj: FormModel) {
        itemView.txtFormTitle.text = obj.title
        itemView.txtFormValue.text = obj.value.toLong().priceAnnotator()
    }

    override fun itemOnClick(item : FormModel, actionSubject: PublishSubject<FormModel>) {

        itemView.clicks()
                .map {
                    adapterPosition
                }
                .filter {
                    it != androidx.recyclerview.widget.RecyclerView.NO_POSITION
                }
                .withLatestFrom(Single.just(item).toObservable(), BiFunction <Int, FormModel, FormModel>{ _, intent->
                    intent
                })
                .subscribe(actionSubject)
    }
}
