package com.gregorymarkthomas.calendar.util.backstack

import com.gregorymarkthomas.calendar.view.LifeCycleView

interface BackStackCallback {
    fun onViewChanged(view: LifeCycleView)
}