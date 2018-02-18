package com.gregorymarkthomas.calendar.util.backstack

import com.gregorymarkthomas.calendar.util.LifeCycleView

interface BackStackInterface {
    fun goTo(item: BackStackItem)
    fun goBack(): Boolean
    fun getCurrentView(): LifeCycleView
}