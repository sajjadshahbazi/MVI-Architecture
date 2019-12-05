package com.sharifin.navigation

import javax.inject.Inject


class NavigationStack @Inject constructor() {
    private val stack = ArrayList<Screen>()

    fun pop(): Screen? {
        val model = stack[stack.lastIndex]
        stack.remove(model)
        return model
    }

    fun canPop(): Boolean {
        if (stack.size > 0) {
            return true
        }
        return false
    }

    fun push(model: Screen) {
        stack.add(model)
    }

    fun peek() =
            ArrayList(stack)
}