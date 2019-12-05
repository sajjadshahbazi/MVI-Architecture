package com.sharifin.widgets.form

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.form_text_with_image.*

class FormTextWithImageViewHolder(
        itemView: View
) : FormViewHolder(itemView) {

    override fun bind(obj: FormModel) {
        txtFormValue.text = obj.value
        img.setImageResource(obj.image)
    }

    override fun itemOnClick(item: FormModel, actionSubject: PublishSubject<FormModel>) {
        //changed here
        itemView.clicks()
                .map {
                    adapterPosition
                }
                .filter {
                    it != RecyclerView.NO_POSITION
                }.map {
                    item
                }
                .subscribe(actionSubject)
    }
}