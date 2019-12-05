package com.sharifin.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class DefaultNavigation(
) : Navigation {
    abstract val stack: NavigationStack

    override fun navBack() {
        if (stack.canPop()) {
            stack.pop()
        }
    }

    override fun finishModule(context: Context) {
        if (stack.canPop()) {
            navBack()
        }
        while (stack.canPop()) {
            val screen = stack.pop()
            if (screen is IsTagged) {
                goto(context, screen)
                return
            }
        }
    }

    override fun goto(
            context: Context,
            screen: Screen,
            needResult: Boolean,
            requestCode: Int
    ) {
        if (screen !is NoStack)
            stack.push(screen)
        if (needResult) {
            (context as Activity).startActivityForResult(
                    getIntent(screen),
                    requestCode
            )
        } else
            context.startActivity(getIntent(screen))
    }

    override fun goto(
            fragment: Fragment,
            screen: Screen,
            needResult: Boolean,
            requestCode: Int
    ) {
        stack.push(screen)
        if (needResult) {
            fragment.startActivityForResult(
                    getIntent(screen),
                    requestCode
            )
        } else
            fragment.startActivity(getIntent(screen))
    }

    private fun popAll() {
        while (stack.canPop()) {
            stack.pop()
        }
    }

    private fun getIntent(screen: Screen): Intent =
            Intent(
                    screen.activityIntentAction
            ).apply {
                `package` = PackageName.PACKAGE_NAME
                putExtras(screen.bundle)
                if (
                        screen is IsTagged
                ) {
                    flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or
                            Intent.FLAG_ACTIVITY_CLEAR_TOP
                } else if (screen is Cleaner) {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                            Intent.FLAG_ACTIVITY_CLEAR_TOP or
                            Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
            }

    override fun saveState(saveInstance: Bundle) {
        val currentStack = stack.peek()
        saveInstance.putParcelableArrayList("navigation", currentStack)

    }

    override fun restoreState(saveInstance: Bundle) {
        val savedStack = saveInstance.getParcelableArrayList<Screen>("navigation")
                ?: return

        val currentStack = stack.peek()

        if (currentStack.isNotEmpty() && currentStack.first() !is Screen.Authentication.LoginScreen) {
            return
        }

        popAll()

        for (screen in savedStack) {
            stack.push(screen)
        }
    }
}