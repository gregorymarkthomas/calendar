package com.gregorymarkthomas.calendar.util.backstack

import com.gregorymarkthomas.calendar.util.LifeCycleView

class BackStack2(private var callback: BackStackCallback): BackStackInterface {

    private var stack: ArrayList<LifeCycleView> = ArrayList()

    override fun goTo(view: LifeCycleView) {
        if(stack.contains(view)) {
            stack.subList(stack.indexOf(view), stack.size - 1).clear()
        } else {
            stack.add(view)
        }

        callback.onViewChanged(view)
    }

    override fun goBack(): Boolean {
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

    override fun getView(): LifeCycleView {
        return stack.get(stack.size - 1)
    }

}