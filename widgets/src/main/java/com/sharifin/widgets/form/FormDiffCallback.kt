package com.sharifin.widgets.form

import androidx.recyclerview.widget.DiffUtil

class FormDiffCallback : DiffUtil.ItemCallback<FormModel>() {

    override fun areItemsTheSame(oldItem: FormModel, newItem: FormModel): Boolean {
        return oldItem.type == newItem.type && oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FormModel, newItem: FormModel): Boolean {
        return oldItem == newItem
    }
}
