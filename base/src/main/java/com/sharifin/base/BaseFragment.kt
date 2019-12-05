package com.sharifin.base

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.view.ViewCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.androidadvance.topsnackbar.TSnackbar
import com.sharifin.base.mvibase.MviIntent
import com.sharifin.base.mvibase.MviView
import com.sharifin.base.mvibase.MviViewState
import com.sharifin.navigation.Navigation
import com.sharifin.navigation.Screen
import com.sharifin.navigation.model.LoginLocalModel
import dagger.Lazy
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.bbbbb.*
import javax.inject.Inject

abstract class BaseFragment<I : MviIntent, S : MviViewState, V : BaseViewModel<I, S>, N : Navigator> : DaggerFragment(),
        MviView<I, S> {

    val destroyableDisposable = CompositeDisposable()
    var tSnackbar: TSnackbar? = null
    var snackBarBottomMargin: Int = 0
    val destroyDisposable = CompositeDisposable()
    val disposables = CompositeDisposable()
    val TagLoadingFragment: String = "LoadingFragment"
    var parentView: ViewGroup? = null
    var viewModelFactory: ViewModelProvider.Factory? = null
    lateinit var viewModel: V
    var loadingView: LoadingView? = null

    @Inject
    lateinit var newNavigator: Navigation
    @Inject
    lateinit var unAuthorizedHandler: com.sharifin.navigation.UnAuthorizedHandler
    var handleUnAuthorized = true


    @Inject
    fun injectViewModelFactory(factory: ViewModelProvider.Factory) {
        viewModelFactory = factory
    }

    private fun bind() {
        disposables.add(
                viewModel.states().subscribe(this::renderState)
        )

        viewModel.processIntents(intents())
    }

    fun createViewModel(clazz: Class<V>) {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(clazz)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createLoadingView()
    }

    override fun onStart() {
        super.onStart()
        bind()
    }


    override fun onStop() {
        disposables.clear()
        super.onStop()
    }

    override fun onDestroyView() {
        destroyDisposable.clear()
        loadingView = null
        parentView = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        destroyableDisposable.clear()
        super.onDestroy()
    }

    override fun onDetach() {
        viewModelFactory = null
        super.onDetach()
    }

    fun showSnackBar(message: String) {
        createAlert(message)
    }

    fun showSnackBar(@StringRes
    message: Int
    ) {
        createAlert(getString(message))
//        snackbar = Snackbar.make(root, message, Snackbar.LENGTH_INDEFINITE)
//                .setActionTextColor(Color.WHITE)
//
//        //change snackbar Text Style ( change font and color)
//        val snackBarText: TextView = snackbar!!.view.findViewById(R.id.snackbar_text)
//        snackBarText.typeface = ResourcesCompat.getFont(context!!, R.font.iransansmobile)
//        snackBarText.setTextColor(Color.WHITE)
//        ViewCompat.setLayoutDirection(snackbar!!.view, ViewCompat.LAYOUT_DIRECTION_RTL)
//        snackbar!!.show()
    }

//    fun showLoading() {
//        loading = AlertDialog.Builder(context!!, R.style.LoadingTheme)
//                .setView(R.layout.dialog_loading)
//                .setOnDismissListener {
//                }
//                .setCancelable(false)
//                .create()
//                .apply {
//                    show()
//                }
//
//    }

    private fun createLoadingView() {
        if (view is ViewGroup) {
            parentView = view as ViewGroup
        } else {
            val parent = view?.parent
            if (parent is ViewGroup) {
                parentView = parent
            }
        }
        parentView?.also {
            loadingView = LoadingView(it.context)
            it.addView(loadingView!!)
            loadingView!!.visibility = View.GONE
        }
    }

    fun dismissTSnackbar() {
        tSnackbar?.run {
            dismiss()
        }
    }

    private fun createAlert(message: String) {
//        Alerter.create(mActivity)
//                .setText(message)
//                .setBackgroundColorRes(R.color.black_0)
////            .setIcon(Alert.getIconRes(type))
////            .enableSwipeToDismiss()
////            .setTextTypeface()
//                .setContentGravity(Gravity.RIGHT)
//                .show()
        tSnackbar = TSnackbar.make(root, message, TSnackbar.LENGTH_INDEFINITE)


        val snackBarLP = tSnackbar!!.view.layoutParams as ViewGroup.MarginLayoutParams
        snackBarLP.setMargins(0, 0, 0, snackBarBottomMargin)
        tSnackbar!!.view.layoutParams = snackBarLP

        //change snackbar Text Style ( change font and color)
        val snackBarText: TextView = tSnackbar!!.view.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text)
        snackBarText.setTextColor(Color.WHITE)
        ViewCompat.setLayoutDirection(tSnackbar!!.view, ViewCompat.LAYOUT_DIRECTION_RTL)

        tSnackbar!!.show()
    }

    private fun renderState(state: S) {
        if (handleUnAuthorized && state.base.error is BaseState.ErrorState.UnAuthorized) {
            val errorMessage = (state.base.error as BaseState.ErrorState.UnAuthorized).errorMessage
            with(activity as Activity) {
                unAuthorizedHandler.userUnAuthorized(this, newNavigator, errorMessage)
            }
        } else {
            render(state)
        }
    }

    fun renderLoading(state: BaseState) {
        if (state.showLoading) {
            if (loadingView != null) {
                loadingView?.apply {
                    visibility = View.VISIBLE
                }
            } else {
                createLoadingView()
                loadingView?.apply {
                    visibility = View.VISIBLE
                }
            }
        } else {
            dismissLoading()
        }
    }

    fun dismissLoading() {
        loadingView?.let {
            it.visibility = View.GONE
        }
    }
}

inline fun <I : MviIntent, S : MviViewState, reified V : BaseViewModel<I, S>> BaseFragment<I, S, V, *>.createViewModel() {
    createViewModel(V::class.java)
}

fun <I : MviIntent, S : MviViewState, V : BaseViewModel<I, S>> BaseFragment<I, S, V, *>.renderError(err: BaseState.ErrorState) {
    if (err.showSnackBar) {
        showSnackBar(err.getErrorString(context!!))
    } else {
        dismissTSnackbar()
    }
}

fun <I : MviIntent, S : MviViewState, V : BaseViewModel<I, S>> BaseFragment<I, S, V, *>.renderError(state: BaseState) {
    renderError(state.error)
}

//fun <I : MviIntent, S : MviViewState, V : BaseViewModel<I, S>> BaseFragment<I, S, V, *>.renderLoading(state: BaseState) {
//    if (state.showLoading) {
//        if (loadingView != null) {
//
//        } else {
//            createloadin
//        }
//        loadingView?.let {
//            if (parentView.indexOfChild(it) == -1) {
//                parentView.addView(it)
//            } else {
//                it.visibility = View.VISIBLE
//            }
//        }
//    } else {
//        dismissLoading()
//    }
//}

