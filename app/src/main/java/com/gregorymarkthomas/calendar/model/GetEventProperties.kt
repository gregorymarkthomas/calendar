package com.gregorymarkthomas.calendar.model

class GetEventProperties(val accountName: String, val dayInMonth: Int, val month: Int,
                         val year: Int, val numberOfDays: Int,
                         val calendarsToShow: IntArray = IntArray(0))