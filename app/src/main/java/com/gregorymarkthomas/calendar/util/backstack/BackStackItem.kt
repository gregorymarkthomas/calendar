package com.gregorymarkthomas.calendar.util.backstack

import com.gregorymarkthomas.calendar.util.LifeCycleView
import com.gregorymarkthomas.calendar.util.interfaces.ContentResolverInterface
import com.gregorymarkthomas.calendar.util.interfaces.LayoutContextInterface
import com.gregorymarkthomas.calendar.util.interfaces.GetSharedPreferencesInterface
import java.util.*

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
class BackStackItem(internal var klass: Class<out LifeCycleView>, internal var date: Date? = null) {

    internal lateinit var view: LifeCycleView

    /**
     * When creating the BackStackItem, the developer supplies the class of the LifeCycleView.
     * This function instantiates that view, and should be called from the activity.
     * This is why the activity is supplied.
     *
     * Each LifeCycleView has an optional Bundle for any input arguments.
     */
    fun instantiateView(backstack: BackStackInterface, resolver: ContentResolverInterface, preferences: GetSharedPreferencesInterface, layoutContext: LayoutContextInterface): LifeCycleView {
        view = klass.getConstructor(BackStackInterface::class.java,
                ContentResolverInterface::class.java,
                GetSharedPreferencesInterface::class.java,
                LayoutContextInterface::class.java, Date::class.java).newInstance(backstack, resolver, preferences, layoutContext, date)
        return view
    }
}