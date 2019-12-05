package com.sharifin.widgets.form

import android.view.View
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.form_text_review.view.*

class FormTextViewHolder(itemView: View) : FormViewHolder(itemView){

    override fun bind(obj: FormModel) {
        itemView.txtFormTitle.text = obj.title
        itemView.txtFormValue.text = obj.value
    }

    override fun itemOnClick(item : FormModel, actionSubject: PublishSubject<FormModel>) {
        //changed here
        itemView.clicks()
                .map {
                    adapterPosition
                }
                .filter {
                    it != androidx.recyclerview.widget.RecyclerView.NO_POSITION
                }.map {
                    item
                }
                .subscribe(actionSubject)
    }
}
