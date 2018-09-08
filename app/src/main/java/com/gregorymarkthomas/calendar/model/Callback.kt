package com.gregorymarkthomas.calendar.model

import com.gregorymarkthomas.calendar.util.AppCalendar
import com.gregorymarkthomas.calendar.util.AppDay

class Callback {
    interface GetCalendarsCallback {
        fun onGetCalendars(calendars: MutableList<AppCalendar>)
    }
    interface GetEventsCallback {
        fun onGetEvents(days: MutableList<AppDay>)
    }
}