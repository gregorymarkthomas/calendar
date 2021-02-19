package com.gregorymarkthomas.calendar.model

import com.gregorymarkthomas.calendar.util.interfaces.GetSharedPreferencesInterface
import java.util.*

class Model(private val calendarRepo: CalendarRepository, preferences: GetSharedPreferencesInterface): ModelInterface {
    private val calendarVisibiltyOption = VisibleCalendarsOption(preferences)

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
        TODO("Not yet implemented")
    }

    override fun setAccount(account: AppAccount) {
        TODO("Not yet implemented")
    }

    override fun getVisibleCalendars(): IntArray {
        return calendarVisibiltyOption.get()
    }

    override fun setVisibleCalendars(calendarsToShow: IntArray) {
        calendarVisibiltyOption.set(calendarsToShow)
    }
}