package com.gregorymarkthomas.calendar.util.backstack

import com.gregorymarkthomas.calendar.view.LifeCycleView
import java.util.*

interface BackStackInterface {
    fun goTo(view: LifeCycleView)
    fun goBack(): Boolean
    fun getMostRecentView(): LifeCycleView
}