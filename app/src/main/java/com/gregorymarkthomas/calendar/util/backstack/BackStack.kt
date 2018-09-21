package com.gregorymarkthomas.calendar.util.backstack

import com.gregorymarkthomas.calendar.model.ModelInterface
import com.gregorymarkthomas.calendar.util.interfaces.AndroidContextInterface
import com.gregorymarkthomas.calendar.view.LifeCycleView
import java.util.*

/**
 * Each view requires a few Android-related items, plus control of the backstack, hence the input arguments.
 * TODO() - make this singleton?
 */
class BackStack(private var callback: BackStackCallback,
                initialView: LifeCycleView): BackStackInterface {

    private var stack: ArrayList<LifeCycleView> = ArrayList()

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
            reset(viewIndex)
        }
        callback.onViewChanged(view)
    }

    override fun goBack(): Boolean {
        val success = try {
            stack.removeAt(stack.size - 1)
            true
        } catch (e: IndexOutOfBoundsException) {
            false
        }
        callback.onViewChanged(getMostRecentView())
        return success
    }

    override fun getMostRecentView(): LifeCycleView {
        return stack[stack.size - 1]
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
    private fun reset(newViewIndex: Int) = stack.subList(0, newViewIndex)
}