package com.sharifin.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.subjects.PublishSubject
import kotlinx.android.extensions.LayoutContainer

abstract class BaseViewHolder<A : BaseViewHolderAction, K>(override val containerView : View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

    abstract fun bind(obj : K)

    abstract fun itemOnClick(actionSubject: PublishSubject<A>)

}