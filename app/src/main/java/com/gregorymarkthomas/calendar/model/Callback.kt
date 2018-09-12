package com.gregorymarkthomas.calendar.model

class Callback {
    interface GetCalendarsCallback {
        fun onGetCalendars(calendars: List<AppCalendar>)
    }
    interface GetEventsCallback {
        fun onGetEvents(days: List<AppDay>)
    }
}