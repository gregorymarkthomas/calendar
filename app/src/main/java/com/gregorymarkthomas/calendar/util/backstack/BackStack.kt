package com.gregorymarkthomas.calendar.util.backstack

import com.gregorymarkthomas.calendar.view.LifeCycleView
import java.util.*

/**
 * Each view requires a few Android-related items, plus control of the backstack, hence the input arguments.
 * TODO() - make this singleton?
 */
class BackStack(private var callback: BackStackCallback, initialView: LifeCycleView): BackStackInterface {

    private var stack: MutableList<LifeCycleView> = mutableListOf()

    init {
        goTo(initialView)
    }

    /********** public */
    /**
     * Either finds the existing instance of the requested viewClass and uses it, or it adds a new instance if it does not yet exist.
     */
    override fun goTo(view: LifeCycleView) {
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

    override fun getMostRecentView(): LifeCycleView {
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
    private fun indexOf(view: LifeCycleView): Int {
        return stack.indexOfLast { it::class.java == view::class.java }
    }

    /**
     * This removes views from the top of the stack to make the requested view the most recent view
     */
    private fun reset(stack: MutableList<LifeCycleView>, newViewIndex: Int): MutableList<LifeCycleView> {
        return stack.subList(0, newViewIndex + 1)
    }

    private fun getMostRecentViewIndex(): Int {
        return stack.size - 1
    }
}