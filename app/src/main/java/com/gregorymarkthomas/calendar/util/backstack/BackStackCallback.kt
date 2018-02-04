package com.gregorymarkthomas.calendar.util.backstack

import android.view.View
import com.gregorymarkthomas.calendar.util.LifeCycleView

interface BackStackCallback {
    fun onViewChanged(view: LifeCycleView)
}