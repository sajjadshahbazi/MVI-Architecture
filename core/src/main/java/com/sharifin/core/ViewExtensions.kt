@file:JvmName("ViewUtils")

package com.sharifin.core

import android.graphics.Typeface
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.tabs.TabLayout

fun TabLayout.changeTabsFont(font: Int) {
    val vg = getChildAt(0) as ViewGroup
    val typeFace = ResourcesCompat.getFont(this.context, font)
    val tabsCount = vg.childCount
    for (j in 0 until tabsCount) {
        val vgTab = vg.getChildAt(j) as ViewGroup
        val tabChildsCount = vgTab.childCount
        for (i in 0 until tabChildsCount) {
            val tabViewChild = vgTab.getChildAt(i)
            if (tabViewChild is TextView) {
                tabViewChild.setTypeface(typeFace, Typeface.NORMAL)
            }
        }
    }
}

fun ViewGroup.inflate(resId: Int): View {
    return LayoutInflater.from(this.context).inflate(resId, this, false)
}

infix fun View.visible(visible: Boolean) {
    visibility = if (visible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

infix fun View.setVisibility(visible: Boolean) {
    visibility = if (visible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

fun EditText.annotatePrice(postFix: String = "ریال") {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            removeTextChangedListener(this)
            val temp: String = s?.toString()
                                       ?.toLongNumber()
                                       ?.priceAnnotator(postFix, postFixDivider = " ")
                               ?: ""
            setText(temp)
            val selection = temp.length - postFix.length - 1
            setSelection(selection)
            addTextChangedListener(this)
        }
    })
}

fun EditText.annotateCardPan(postFix: Char = ' ') {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) = Unit

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            removeTextChangedListener(this)
            val temp: String = s?.toString()
                                       ?.toNumberString()
                                       ?.cardPanFormatter(postFix)
                               ?: ""
            setText(temp)
            val selection = temp.length
            setSelection(selection)
            addTextChangedListener(this)
        }
    })
}

fun EditText.annotatePhone(postFix: Char = ' ') {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) = Unit

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            removeTextChangedListener(this)
            val temp: String = s?.toString()
                                       ?.toNumberString()
                                       ?.phoneFormatter(postFix)
                               ?: ""
            setText(temp)
            val selection = temp.length
            setSelection(selection)
            addTextChangedListener(this)
        }
    })
}