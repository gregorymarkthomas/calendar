package com.gregorymarkthomas.calendar.util

import android.support.constraint.ConstraintLayout
import android.view.ViewGroup
import com.gregorymarkthomas.calendar.MainActivity
import com.gregorymarkthomas.calendar.util.backstack.BackStackInterface


abstract class LifeCycleView: ConstraintLayout {

    var backstack: BackStackInterface

    constructor(activity: MainActivity): super(activity) {
        backstack = activity
        inflate()
    }

    /**
     * TODO - is there TWO constraint layouts?? LifeCycleView implements ConstraintLayout, AND month_view.xml has ConstraintLayout as root...
     */
    private fun inflate() {
        inflate(context, getLayout(), this)

        /** Expand 'this' ConstraintLayout to use all of the usable space. **/
        layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    abstract fun getLayout(): Int
}
