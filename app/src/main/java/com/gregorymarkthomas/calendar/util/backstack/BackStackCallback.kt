package com.gregorymarkthomas.calendar.util.backstack

import android.view.View

interface BackStackCallback {
    fun onViewChanged(view: View)
}