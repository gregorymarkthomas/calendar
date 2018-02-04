package com.gregorymarkthomas.calendar.util

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import com.gregorymarkthomas.calendar.presenter.CalendarPresenter
import com.gregorymarkthomas.calendar.presenter.CalendarPresenterInterface


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
        onViewReady()
    }

    abstract fun getLayout(): Int
    abstract fun onViewReady()
}
