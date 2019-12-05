package com.sharifin.widgets.form

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.subjects.PublishSubject
import kotlinx.android.extensions.LayoutContainer

abstract class FormViewHolder(
        override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    abstract fun bind(obj: FormModel)


    abstract fun itemOnClick(item: FormModel, actionSubject: PublishSubject<FormModel>)
}