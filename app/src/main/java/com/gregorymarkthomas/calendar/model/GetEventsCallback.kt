package com.gregorymarkthomas.calendar.model

import com.gregorymarkthomas.calendar.util.Day

interface GetEventsCallback {
    fun onGetEvents(days: MutableList<Day>)
}