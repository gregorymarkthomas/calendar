package com.gregorymarkthomas.calendar.model

import java.text.DateFormatSymbols
import java.util.*

class Model: ModelInterface {
    override fun getTodayDate(): Date {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return Date()
    }

    override fun getEvents(dayInMonth: Int, month: Int, year: Int, numberOfDays: Int?) {
        if(numberOfDays == null)
            numberOfDays = getDaysInMonth(month, year)

        // TODO() - contact calendar provider here
    }

    override fun getMonthString(month: Int) = DateFormatSymbols().months[month-1]

    private fun getDaysInMonth(month: Int, year: Int): Int {
        TODO()
    }
}