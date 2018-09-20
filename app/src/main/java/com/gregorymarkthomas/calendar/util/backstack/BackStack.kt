package com.gregorymarkthomas.calendar.util.backstack

import com.gregorymarkthomas.calendar.model.ModelInterface
import com.gregorymarkthomas.calendar.util.interfaces.LayoutContextInterface
import com.gregorymarkthomas.calendar.view.LifeCycleView
import java.util.*

/**
 * Each view requires a few Android-related items, plus control of the backstack, hence the input arguments.
 * TODO() - make this singleton?
 */
class BackStack(private var callback: BackStackCallback,
                initialView: Class<out LifeCycleView>,
                selectedDate: Date,
                private val backstack: BackStackInterface,
                private val model: ModelInterface,
                private val layout: LayoutContextInterface): BackStackInterface {

    private var stack: ArrayList<BackStackItem> = ArrayList()

    init {
        goTo(initialView, selectedDate)
    }

    /********** public */
    /**
     * Either finds the existing instance of the requested viewClass and uses it, or it adds a new instance if it does not yet exist.
     */
    override fun goTo(viewClass: Class<out LifeCycleView>, selectedDate: Date) {
        val item: BackStackItem
        val viewIndex = indexOf(viewClass)
        if(viewIndex == -1) {
            item = BackStackItem(viewClass, selectedDate, backstack, model, layout)
            stack.add(item)
        } else {
            reset(viewIndex)
            item = getRecent()
            /** Refresh the input parameters of the viewClass we are 're-using' **/
            item.date = selectedDate
        }
        callback.onViewChanged(item.view)
    }

    override fun goBack(): Boolean {
        val success = try {
            stack.removeAt(stack.size - 1)
            true
        } catch (e: IndexOutOfBoundsException) {
            false
        }
        callback.onViewChanged(getRecent().view)
        return success
    }

    override fun getRecentViewClass(): Class<out LifeCycleView> {
        return getRecent().view::class.java
    }


    /********** private */
    private fun getRecent(): BackStackItem {
        return stack[stack.size - 1]
    }

    /**
     * Looks in the backstack for the given View
     */
    private fun indexOf(view: Class<out LifeCycleView>): Int {
        return stack.indexOfLast { it.klass == view }
    }

    /**
     * This removes views from the top of the stack to make the requested view the most recent view
     */
    private fun reset(newViewIndex: Int) = stack.subList(0, newViewIndex)


    /**
     * This class holds the class of the view we want to add to the BackStack. It also holds any input arguments.
     * This means that, using an instance of BackStackInterface, the developer can do this:
     *      val args = Bundle()
     *      args.put(Date())
     *      BackStackInterface.goTo(MonthView::class.java, args)
     *
     * 'args' are optional - an empty 'args' will be used if a Bundle is not supplied.
     *
     * Each individual LifeCycleView child has to unpack the Bundle.
     */
    private class BackStackItem(val klass: Class<out LifeCycleView>,
                                var date: Date,
                                backstack: BackStackInterface,
                                model: ModelInterface,
                                layout: LayoutContextInterface) {

        val view: LifeCycleView

        init {
            view = instantiateView(date, backstack, model, layout)
        }

        /**
         * When creating the BackStackItem, the developer supplies the class of the LifeCycleView.
         * This function instantiates that view, and should be called from the activity.
         * This is why the activity is supplied.
         *
         * Each LifeCycleView has an optional Bundle for any input arguments.
         */
        private fun instantiateView(date: Date, backstack: BackStackInterface, model: ModelInterface, layout: LayoutContextInterface): LifeCycleView {
            return klass.getConstructor(BackStackInterface::class.java, ModelInterface::class.java, LayoutContextInterface::class.java, Date::class.java).newInstance(backstack, model, layout, date)
        }
    }
}