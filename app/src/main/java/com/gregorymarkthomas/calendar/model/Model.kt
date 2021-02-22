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

    override fun getEvents(account: AppAccount, dayInMonth: Int, month: Int, year: Int, numberOfDays: Int, callback: Callback.GetEventsCallback, calendarsToShow: IntArray) {
        calendarRepo.getEvents(account, dayInMonth, month, year, numberOfDays, callback, calendarsToShow)
    }

    override fun getCalendars(account: AppAccount, callback: Callback.GetCalendarsCallback) {
        calendarRepo.getCalendars(account, callback)
    }

    override fun getAvailableAccounts(callback: Callback.GetAccountsCallback) {
        calendarRepo.getAccounts(callback)
    }

    override fun getSavedAccount(): AppAccount? {
        return accountRepo.getAccount()
    }

    override fun setAccount(account: AppAccount) {
        accountRepo.setAccount(account)
    }

    override fun getVisibleCalendars(): IntArray {
        return calendarVisibilityRepo.getVisibleCalendars()
    }

    override fun setVisibleCalendars(calendarsToShow: IntArray) {
        calendarVisibilityRepo.setVisibleCalendars(calendarsToShow)
    }
}