package com.gregorymarkthomas.calendar.util.backstack

import com.gregorymarkthomas.calendar.view.BackStackView
import java.util.*

/**
 * Each view requires a few Android-related items, plus control of the backstack, hence the input arguments.
 * This is not a singleton in case the app has multiple Activities - there will be one BackStack per Activity.
 */
class BackStack(private var callback: BackStackCallback, initialView: BackStackView): BackStackInterface {

    private var stack: MutableList<BackStackView> = mutableListOf()

    init {
        goTo(initialView)
    }

    /********** public */
    /**
     * Either finds the existing instance of the requested viewClass and uses it, or it adds a new instance if it does not yet exist.
     */
    override fun goTo(view: BackStackView) {
        val viewIndex = indexOf(view)
        if(viewIndex == -1) {
            stack.add(view)
        } else {
            stack = reset(stack, viewIndex)
        }
        callback.onViewChanged(view)
    }

    override fun goBack(): Boolean {
        val success = try {
            if(getMostRecentViewIndex() != 0) {
                stack.removeAt(getMostRecentViewIndex())
                true
            } else
                false
        } catch (e: ArrayIndexOutOfBoundsException) {
            false
        }
        callback.onViewChanged(getMostRecentView())
        return success
    }

    override fun getMostRecentView(): BackStackView {
        return stack[getMostRecentViewIndex()]
    }

    override fun getCurrentViewClasses(): List<String> {
        val classNames = ArrayList<String>(stack.size)
        for (view in stack) {
            classNames.add(view::class.simpleName!!)
        }
        return classNames
    }

    /********** private */
    /**
     * Looks in the backstack for the given View
     */
    private fun indexOf(view: BackStackView): Int {
        return stack.indexOfLast { it::class.java == view::class.java }
    }

    /**
     * This removes views from the top of the stack to make the requested view the most recent view
     */
    private fun reset(stack: MutableList<BackStackView>, newViewIndex: Int): MutableList<BackStackView> {
        return stack.subList(0, newViewIndex + 1)
    }

    /**
     * Retrieves the index for the last item in the stack.
     */
    private fun getMostRecentViewIndex(): Int {
        return stack.size - 1
    }
}