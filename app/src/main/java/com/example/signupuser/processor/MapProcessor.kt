package com.example.signupuser.processor

import com.example.signupuser.action.MapAction
import com.example.signupuser.result.MapResult
import com.sharifin.base.mvibase.MviProcessor
import com.sharifin.core.defaultClicksLastStableResultWithStart
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import javax.inject.Inject

class MapProcessor @Inject constructor(
) : MviProcessor<MapAction, MapResult> {
    override val actionProcessor: ObservableTransformer<MapAction, MapResult> =
        ObservableTransformer { actions ->
            actions.publish { publish ->
                Observable.merge(
                    listOf(
                        publish.ofType(MapAction.InitialAction::class.java)
                            .compose(initial),
                        publish.ofType(MapAction.ConfirmIntent::class.java)
                            .compose(confirm)
                    )
                )
            }
        }

    private val initial = ObservableTransformer<MapAction.InitialAction, MapResult> { actions ->
        actions.switchMap {
            Observable.just(MapResult.LastStable)
        }
    }
    private val confirm = ObservableTransformer<MapAction.ConfirmIntent, MapResult> { actions ->
        actions.switchMap {
            defaultClicksLastStableResultWithStart(
                MapResult.LastStable,
                MapResult.Confirm(
                    lat = it.lat,
                    lng = it.lng
                )

            )
        }
    }
}