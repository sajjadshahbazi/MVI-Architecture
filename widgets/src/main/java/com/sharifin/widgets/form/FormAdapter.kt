package com.sharifin.widgets.form

import android.view.ViewGroup
import android.webkit.WebView.HitTestResult.EDIT_TEXT_TYPE
import androidx.recyclerview.widget.ListAdapter
import com.sharifin.core.inflate
import com.sharifin.widgets.R
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class FormAdapter : ListAdapter<FormModel, FormViewHolder>(FormDiffCallback()) {

    private val clickPs = PublishSubject.create<FormModel>()

    override fun getItemViewType(position: Int): Int {
        return getItem(position).type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormViewHolder {
        when (viewType) {
            TEXT_VIEW_TYPE ->
                return FormTextViewHolder(parent.inflate(R.layout.form_text_review))
            TEXT_VIEW_TYPE_NORMAL ->
                return FormTextViewHolder(parent.inflate(R.layout.form_text_normal))
            EDIT_TEXT_TYPE ->
                return FormPriceEditTextViewHolder(parent.inflate(R.layout.form_edit_text_review))
            EDIT_TEXT_TYPE_NORMAL ->
                return FormNormalEditTextViewHolder(parent.inflate(R.layout.form_edit_text_normal))
            EDIT_TEXT_TYPE_NORMAL_NUMBER ->
                return FormNormalEditTextViewHolder(parent.inflate(R.layout.form_edit_text_normal_number))
            TEXT_WITH_IMAGE_VIEW_TYPE ->
                return FormTextWithImageViewHolder(parent.inflate(R.layout.form_text_with_image))
        }
        return FormTextViewHolder(parent.inflate(R.layout.form_text_review)) // default
    }

    override fun onBindViewHolder(holder: FormViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemOnClick(getItem(position), clickPs)
    }

    fun getClickPs(): Observable<FormModel> {
        return clickPs
    }

}