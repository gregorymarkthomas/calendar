package com.gregorymarkthomas.calendar.util.backstack

import com.gregorymarkthomas.calendar.view.BackStackView

interface BackStackInterface {
    fun goTo(view: BackStackView)
    fun clearTo(view: BackStackView)
    fun goBack(): Boolean
    fun getMostRecentView(): BackStackView

    /**
     * Merely returns List<String> - this is for status/testing purposes.
     */
    fun getCurrentViewClasses(): List<String>
}