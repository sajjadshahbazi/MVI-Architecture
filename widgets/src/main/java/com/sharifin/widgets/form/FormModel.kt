package com.sharifin.widgets.form

import androidx.annotation.DrawableRes

data class FormModel(
        val id: Int,
        val title: String,
        val value: String,
        @DrawableRes val image: Int = 0,
        val type: Int = TEXT_VIEW_TYPE,
        val hasFocus: Boolean = false) {
    override fun toString(): String {
        return """FormModel{
            id: $id
            title: $title
            value: $value
            type:  $type
        }""".trimIndent()
    }
}