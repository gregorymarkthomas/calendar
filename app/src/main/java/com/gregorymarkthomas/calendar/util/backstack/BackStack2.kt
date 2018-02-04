package com.gregorymarkthomas.calendar.util.backstack

import com.gregorymarkthomas.calendar.util.LifeCycleView

object BackStack2 {

    private var stack: ArrayList<LifeCycleView> = ArrayList()

    private lateinit var callback: BackStackCallback

    fun setCallback(callback: BackStackCallback) {
        this.callback = callback
    }

    fun goTo(view: LifeCycleView) {
        if(stack.contains(view)) {
            stack.subList(stack.indexOf(view), stack.size - 1).clear()
        } else {
            stack.add(view)
        }

        callback.onViewChanged(view)
    }

    fun goBack(): Boolean {
        var success: Boolean
        try {
            stack.removeAt(stack.size - 1)
            success = true
        } catch (e: IndexOutOfBoundsException) {
            success = false
        }

        callback.onViewChanged(getView())

        return success
    }

    fun getView(): LifeCycleView {
        return stack.get(stack.size - 1)
    }

//    fun getHistory(): ArrayList<View> = stack
//    fun setHistory(history: ArrayList<View>) {
//        stack = history
//    }


}