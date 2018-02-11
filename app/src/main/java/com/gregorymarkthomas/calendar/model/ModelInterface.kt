package com.gregorymarkthomas.calendar.model

import java.util.*

interface ModelInterface {
    fun getTodayDate(): Date
    fun getMonthString(month: Int): String
    fun getEvents(dayInMonth: Int, month: Int, year: Int, numberOfDays: Int?)
}