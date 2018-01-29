package com.gregorymarkthomas.calendar.util.backstack

import android.os.Parcelable
import android.view.View

interface BackStackInterface {
    fun goTo(view: View)
    fun goBack(): Boolean
}