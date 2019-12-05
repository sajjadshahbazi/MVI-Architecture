package com.sharifin.core

import android.widget.EditText
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.disposables.Disposable

fun EditText.rxPriceAnnotator(postfix: String = "",
                              maxLength: Int = Int.MAX_VALUE): Disposable =
        RxTextView.textChangeEvents(this)
                .filter {
                    it.count() < 2 && it.before() < 2 && it.start() > 0


                }
                .map {
                    if (it.text().isNotBlank()) {
                        val temp: String = it.text().toString().toLongNumber().priceAnnotator(postfix)
                        it.view().setText(temp)
                        val selection = temp.length
                        if (selection < maxLength)
                            (it.view() as EditText).setSelection(selection)
                        else
                            (it.view() as EditText).setSelection(10)
                    }
                }
                .doOnError {
                    it.printStackTrace()
                }
                .subscribe()