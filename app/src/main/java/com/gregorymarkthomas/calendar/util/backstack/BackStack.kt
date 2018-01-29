package com.gregorymarkthomas.calendar.util.backstack

import android.view.View
import com.gregorymarkthomas.calendar.util.SingletonHolder

/** With thanks to @BladeCoder (Christophe Beyls) for the tips to pass arguments to Singletons.
 * https://medium.com/@BladeCoder/kotlin-singletons-with-argument-194ef06edd9e **/
class BackStack private constructor(var callback: BackStackCallback): BackStackInterface {

    private var stack: ArrayList<View> = ArrayList()

    init {}

    companion object: SingletonHolder<BackStack, BackStackCallback>(::BackStack)

    override fun goTo(view: View) {
        if(stack.contains(view)) {
            stack.subList(stack.indexOf(view), stack.size - 1).clear()
        } else {
            stack.add(view)
        }

        callback.onViewChanged(stack.get(stack.size - 1))
    }

    override fun goBack(): Boolean {
        var success: Boolean
        try {
            stack.removeAt(stack.size - 1)
            success = true
        } catch (e: IndexOutOfBoundsException) {
            success = false
        }

        callback.onViewChanged(stack.get(stack.size - 1))

        return success
    }

    fun getHistory(): ArrayList<View> = stack
    fun setHistory(history: ArrayList<View>) {
        stack = history
    }


}