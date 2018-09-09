package com.gregorymarkthomas.calendar.model

class Callback {
    interface GetCalendarsCallback {
        fun onGetCalendars(calendars: MutableList<AppCalendar>)
    }
    interface GetEventsCallback {
        fun onGetEvents(days: MutableList<AppDay>)
    }
}