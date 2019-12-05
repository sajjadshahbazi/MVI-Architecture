package com.sharifin.base

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout


class LoadingView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.view_loading, this)
    }
}
