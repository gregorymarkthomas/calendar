package com.gregorymarkthomas.calendar.util.backstack

import com.gregorymarkthomas.calendar.view.BackStackView

interface BackStackCallback {
    fun onViewChanged(backstackView: BackStackView)
}