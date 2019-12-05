package com.sharifin.base.mvibase

import io.reactivex.ObservableTransformer

interface MviProcessor<A : MviAction, R : MviResult> {

    val actionProcessor : ObservableTransformer<A, R>
}