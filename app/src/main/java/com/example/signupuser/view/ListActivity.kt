package com.example.signupuser.view

import android.os.Bundle
import android.view.View
import com.airbnb.epoxy.EpoxyVisibilityTracker
import com.example.signupuser.R
import com.example.signupuser.intent.ListIntent
import com.example.signupuser.model.UserEpoxyModel
import com.example.signupuser.navigator.ListNavigator
import com.example.signupuser.state.ListState
import com.example.signupuser.viewmodels.ListViewModel
import com.sharifin.base.BaseActivity
import com.sharifin.base.createViewModel
import com.sharifin.base.renderError
import com.sharifin.base.renderLoading
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : BaseActivity<
        ListIntent,
        ListState,
        ListViewModel,
        ListNavigator
        >() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        createViewModel()
        txtToolbarTitle.text = ""
        btnToolbarBack.visibility = View.GONE
        EpoxyVisibilityTracker().attach(listUsers)
    }

    override fun intents(): Observable<ListIntent> =
        Observable.merge(
            listOf(
                Observable.just(ListIntent.InitialIntent)
            )
        )

    override fun onBackPressed() {}

    override fun render(state: ListState) {
        renderLoading(state.base)
        renderError(state.base)

        listUsers.withModels {
            state.userUiModels.forEachIndexed { index, userUiModel ->

                UserEpoxyModel(
                    address = userUiModel.address,
                    lastName = userUiModel.lastName,
                    firstName = userUiModel.firstName
                ).id(userUiModel.id)
                .addTo(this)

            }
//            UserEpoxyModel(
//                state,
//                index
//            ).setClick {
//                bannerClick?.invoke(this@SliderItem, bundleOf(
//                    "model" to slide,
//                    "position" to index
//                ).getParcelable("model")!!)
//            }.id(slide.id)
//                .addTo(this)
        }
    }
}