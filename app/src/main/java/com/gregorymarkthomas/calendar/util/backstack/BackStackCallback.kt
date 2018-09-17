package com.gregorymarkthomas.calendar.util.backstack

interface BackStackCallback {
    fun onViewChanged(item: BackStackItem)
}