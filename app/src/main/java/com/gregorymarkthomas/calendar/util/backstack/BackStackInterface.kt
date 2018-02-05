package com.gregorymarkthomas.calendar.util.backstack

import android.os.Parcelable
import android.view.View
import com.gregorymarkthomas.calendar.util.LifeCycleView

interface BackStackInterface {
    fun goTo(item: BackStackItem)
    fun goBack(): Boolean
    fun getView(): BackStackItem
}