package com.sharifin.base

import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.androidadvance.topsnackbar.TSnackbar
import com.google.android.material.snackbar.Snackbar
import com.sharifin.base.mvibase.MviIntent
import com.sharifin.base.mvibase.MviView
import com.sharifin.base.mvibase.MviViewState
import dagger.Lazy
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.bbbbb.*
import javax.inject.Inject

abstract class BaseActivity<I : MviIntent, S : MviViewState, V : BaseViewModel<I, S>, N : Navigator> :
        NavigatorActivity(),
        MviView<I, S> {


    @Inject
    lateinit var unAuthorizedHandler: com.sharifin.navigation.UnAuthorizedHandler

    val destroyDisposable = CompositeDisposable()
    val disposables = CompositeDisposable()
    var snackbar: Snackbar? = null
    var tSnackbar: TSnackbar? = null

    var snackBarBottomMargin: Int = 0

    val loading: AlertDialog by lazy {
        AlertDialog.Builder(this, R.style.LoadingTheme)
                .setView(R.layout.dialog_loading)
                .setOnDismissListener {
                }
                .setCancelable(false)
                .create()
    }

    lateinit var txtToolbarTitle: TextView
    lateinit var btnToolbarBack: ImageView
    lateinit var toolbar: Toolbar

    var handleUnAuthorized = true

    var viewModelFactory: ViewModelProvider.Factory? = null
    lateinit var viewModel: V

    @Inject
    fun injectViewModelFactory(factory: ViewModelProvider.Factory) {
        viewModelFactory = factory
    }

//    private var navigatorProvider: Lazy<N>? = null
//    val navigator: N?
//        get() = navigatorProvider?.get()
//
//    @Inject
//    fun injectNavigator(navigator: Lazy<N>) {
//        this.navigatorProvider = navigator
//    }

    private fun bind() {
        disposables.add(
                viewModel.states().subscribe(this::renderState)
        )

        viewModel.processIntents(intents())
    }

    fun createViewModel(clazz: Class<V>) {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(clazz)
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        txtToolbarTitle = findViewById(R.id.txtToolbarTitle)
        btnToolbarBack = findViewById(R.id.btnToolbarBack)
        toolbar = findViewById(R.id.toolbar)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_left, R.anim.slide_left_out)
    }


    override fun onStart() {
        super.onStart()
        bind()
    }

    override fun onStop() {
        disposables.clear()
        loading.dismiss()
        super.onStop()
    }

    override fun onDestroy() {
        destroyDisposable.clear()
//        navigatorProvider = null
        super.onDestroy()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right)
    }

    private fun renderState(state: S) {


        if (handleUnAuthorized && state.base.error is BaseState.ErrorState.UnAuthorized) {
            val errorMessage = (state.base.error as BaseState.ErrorState.UnAuthorized).errorMessage
            unAuthorizedHandler.userUnAuthorized(this, newNavigator, errorMessage)
        } else {
            render(state)
        }
    }

    fun dismissTSnackbar() {
        tSnackbar?.run {
            dismiss()
        }
    }

    private fun createAlert(message: String) {
        tSnackbar = TSnackbar.make(root, message, TSnackbar.LENGTH_INDEFINITE)

        val snackBarLP = tSnackbar!!.view.layoutParams as ViewGroup.MarginLayoutParams
        snackBarLP.setMargins(0, 0, 0, snackBarBottomMargin)
        tSnackbar!!.view.layoutParams = snackBarLP

        //change snackbar Text Style ( change font and color)
        val snackBarText: TextView =
            tSnackbar!!.view.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text)
        snackBarText.setTextColor(Color.WHITE)
        ViewCompat.setLayoutDirection(tSnackbar!!.view, ViewCompat.LAYOUT_DIRECTION_RTL)

        tSnackbar!!.show()


    }

    fun showSnackBar(message: String) {
        if (tSnackbar != null) {
            if (tSnackbar!!.isShown) {
                val snackBarText: TextView = tSnackbar!!.view.findViewById(R.id.snackbar_text)
                if (message != snackBarText.text)
                    createAlert(message)
            } else {
                createAlert(message)

            }
        } else {
            createAlert(message)
        }
    }

    fun showSnackBar(@StringRes
    message: Int
    ) {
        if (snackbar != null) {
            if (snackbar!!.isShown) {
                val snackBarText: TextView = snackbar!!.view.findViewById(R.id.snackbar_text)
                if (getString(message) != snackBarText.text)
                    createAlert(getString(message))
            } else {
                createAlert(getString(message))
            }
        } else {
            createAlert(getString(message))
        }
    }

    private fun createAndShowSnackBar(message: String) {
        snackbar = Snackbar.make(root, message, Snackbar.LENGTH_INDEFINITE)
                .setActionTextColor(Color.WHITE)

        //adding a margin for system navigation
        val snackBarLP = snackbar!!.view.layoutParams as ViewGroup.MarginLayoutParams
        snackBarLP.setMargins(0, 0, 0, snackBarBottomMargin)
        snackbar!!.view.layoutParams = snackBarLP

        //change snackbar Text Style ( change font and color)
        val snackBarText: TextView = snackbar!!.view.findViewById(R.id.snackbar_text)
        snackBarText.setTextColor(Color.WHITE)

        ViewCompat.setLayoutDirection(snackbar!!.view, ViewCompat.LAYOUT_DIRECTION_RTL)
        snackbar!!.show()
    }


    fun showLoading() {
        loading.show()
    }

    fun dismissLoading() {
        loading.dismiss()
    }
}


inline fun <I : MviIntent, S : MviViewState, reified V : BaseViewModel<I, S>> BaseActivity<I, S, V, *>.createViewModel() {
    createViewModel(V::class.java)
}

fun <I : MviIntent, S : MviViewState, V : BaseViewModel<I, S>> BaseActivity<I, S, V, *>.renderError(err: BaseState.ErrorState) {
    if (err.showSnackBar) {
        showSnackBar(err.getErrorString(this))
    } else {
        dismissTSnackbar()
    }
}

fun <I : MviIntent, S : MviViewState, V : BaseViewModel<I, S>> BaseActivity<I, S, V, *>.renderError(state: BaseState) {
    renderError(state.error)
}

fun <I : MviIntent, S : MviViewState, V : BaseViewModel<I, S>> BaseActivity<I, S, V, *>.renderLoading(state: BaseState) {
    if (state.showLoading) {
        showLoading()
    } else {
        dismissLoading()
    }
}

