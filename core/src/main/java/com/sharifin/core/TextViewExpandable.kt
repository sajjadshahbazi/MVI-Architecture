package com.sharifin.core

import android.text.TextUtils
import android.widget.TextView
import com.jakewharton.rxbinding2.view.RxView.clicks
import io.reactivex.Observable

fun TextView.expandable(): Unit =
    setOnClickListener {
        if (this.ellipsize == null) {
            maxLines = 10
            ellipsize = TextUtils.TruncateAt.END
        } else {
            maxLines = 30
            ellipsize = null
        }
    }

fun TextView.enableExpandableObserveClicks(): Observable<Boolean> =
    clicks(this)
            .map {
                this.ellipsize == null
            }
            .doOnNext {
                if (it) {
                    maxLines = 10
                    ellipsize = TextUtils.TruncateAt.END
                } else {
                    maxLines = 30
                    ellipsize = null
                }
            }