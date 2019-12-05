package com.sharifin.base

import androidx.lifecycle.ViewModel
import com.sharifin.base.mvibase.MviIntent
import com.sharifin.base.mvibase.MviViewModel
import com.sharifin.base.mvibase.MviViewState
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<I : MviIntent, S : MviViewState> : ViewModel(),
        MviViewModel<I, S> {

    val disposables by lazy { CompositeDisposable() }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}