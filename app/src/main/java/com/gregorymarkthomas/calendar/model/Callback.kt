package com.gregorymarkthomas.calendar.model

import com.gregorymarkthomas.calendar.util.Day

class Callback {
    interface GetEventsCallback {
        fun onGetEvents(days: MutableList<Day>)
    }
}