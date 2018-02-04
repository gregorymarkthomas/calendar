package com.gregorymarkthomas.calendar.util

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.ViewGroup


abstract class LifeCycleView: ConstraintLayout {

    constructor(context: Context) : super(context) {
        inflate()
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        inflate()
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        inflate()
    }

    /**
     * TODO - is there TWO constraint layouts?? LifeCycleView implements ConstraintLayout, AND month_view.xml has ConstraintLayout as root...
     */
    private fun inflate() {
        inflate(context, getLayout(), this)

        /** Expand 'this' ConstraintLayout to use all of the usable space. **/
        layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        onViewReady()
    }

    abstract fun getLayout(): Int
    abstract fun onViewReady()
}
