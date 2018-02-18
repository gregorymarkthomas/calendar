package com.gregorymarkthomas.calendar.util

import android.support.constraint.ConstraintLayout
import android.view.ViewGroup
import com.gregorymarkthomas.calendar.MainActivity
import com.gregorymarkthomas.calendar.util.backstack.BackStackInterface


abstract class LifeCycleView: ConstraintLayout {

    var backstack: BackStackInterface

    constructor(activity: MainActivity): super(activity) {
        backstack = activity
        tag = javaClass.simpleName
        inflate()
    }

    /**
     * The <merge> tag has been used in each view that uses the LifeCycleView.
     * This is because LifeCycleView already subclasses ConstraintLayout.
     *      If month_view.xml also has a ConstraintLayout as the root, then there will be too many ViewGroups.
     *      We now use <merge> to use the higher level ViewGroup.
     */
    private fun inflate() {
        inflate(context, getLayout(), this)

        /** Expand 'this' ConstraintLayout to use all of the usable space. **/
        layoutParams = ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    abstract fun getLayout(): Int
}
