package com.gregorymarkthomas.calendar.model

import com.gregorymarkthomas.calendar.model.repository.CalendarVisibilityRepository
import com.gregorymarkthomas.calendar.model.repository.AccountRepository
import com.gregorymarkthomas.calendar.model.repository.CalendarRepository
import java.util.*

class Model(private val calendarRepo: CalendarRepository,
            private val accountRepo: AccountRepository,
            private val calendarVisibilityRepo: CalendarVisibilityRepository): ModelInterface {

    override fun getTodayDate(): Date {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return Date()
    }

    override fun getEvents(accountName: String, dayInMonth: Int, month: Int, year: Int, numberOfDays: Int, callback: Callback.GetEventsCallback, calendarsToShow: IntArray) {
        calendarRepo.getEvents(accountName, dayInMonth, month, year, numberOfDays, callback, calendarsToShow)
    }

    override fun getCalendars(accountName: String, callback: Callback.GetCalendarsCallback) {
        calendarRepo.getCalendars(accountName, callback)
    }

    override fun getAvailableAccounts(callback: Callback.GetAccountsCallback) {
        calendarRepo.getAccounts(callback)
    }

    override fun getAccount(accountName: String, callback: Callback.GetAccountCallback) {
        calendarRepo.getAccount(accountName, callback)
    }

    override fun getSavedAccount(callback: Callback.GetAccountCallback) {
        val accountName = accountRepo.getData()
        if(accountName.isNullOrEmpty())
            callback.onGetAccount(null)
        else
            calendarRepo.getAccount(accountName, callback)
    }

    override fun setAccount(accountName: String) {
        accountRepo.setData(accountName)
    }

    override fun getVisibleCalendars(): IntArray {
        return calendarVisibilityRepo.getData()
    }

    override fun setVisibleCalendars(calendarsToShow: IntArray) {
        calendarVisibilityRepo.setData(calendarsToShow)
    }
}