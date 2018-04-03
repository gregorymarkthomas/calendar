package com.gregorymarkthomas.calendar.util.backstack

import com.gregorymarkthomas.calendar.util.LifeCycleView

class BackStack2(private var callback: BackStackCallback): BackStackInterface {

    private var stack: ArrayList<BackStackItem> = ArrayList()

    fun indexOf(item: BackStackItem): Int {
        return stack.indexOfLast { it.klass == item.klass }
    }

    override fun goTo(item: BackStackItem) {
        val index = indexOf(item)
        if(index == -1) {
            stack.add(item)
        } else {
            stack.subList(index, stack.size - 1).clear()

            /** Refresh the input parameters of the view we are 're-using' **/
            getCurrentBackStackItem().date = item.date
        }

        callback.onViewChanged(item)
    }

    override fun goBack(): Boolean {
        var success: Boolean
        try {
            stack.removeAt(stack.size - 1)
            success = true
        } catch (e: IndexOutOfBoundsException) {
            success = false
        }

        callback.onViewChanged(getCurrentBackStackItem())

        return success
    }

    override fun getCurrentView(): LifeCycleView {
        return getCurrentBackStackItem().view
    }

    private fun getCurrentBackStackItem(): BackStackItem {
        return stack.get(stack.size - 1)
    }
}