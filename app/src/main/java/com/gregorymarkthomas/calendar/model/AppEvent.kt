package com.gregorymarkthomas.calendar.model

import java.util.*

class AppEvent(val id: Int,
               val calendar: AppCalendar,
               val startDate: Date,
               val endDate: Date,
               val isAllDay: Boolean,
               val title: String,
               val description: String,
               val colourResource: Int,
               val location: String,
               val timeZone: String,
               val endTimeZone: String,
               val hasAlarm: Boolean) {

}