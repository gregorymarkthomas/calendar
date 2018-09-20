package com.gregorymarkthomas.calendar.util.backstack

import com.gregorymarkthomas.calendar.view.LifeCycleView
import java.util.*

interface BackStackInterface {
    /** TODO() - change 'selectedDate' for generic Bundle **/
    fun goTo(viewClass: Class<out LifeCycleView>, selectedDate: Date)
    fun goBack(): Boolean
    fun getCurrentView(): LifeCycleView
}