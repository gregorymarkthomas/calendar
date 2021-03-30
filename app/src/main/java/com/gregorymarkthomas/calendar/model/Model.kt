package com.gregorymarkthomas.calendar.model

import com.gregorymarkthomas.calendar.model.repository.AccountRepository
import com.gregorymarkthomas.calendar.model.repository.CalendarRepository
import com.gregorymarkthomas.calendar.model.repository.CalendarVisibilityRepository
import java.util.*

class Model(private val calendarRepo: CalendarRepository,
            private val accountRepo: AccountRepository,
            private val calendarVisibilityRepo: CalendarVisibilityRepository): ModelInterface {

    override fun getTodayDate(): Date {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return Date()
    }

    override fun getEvents(properties: GetEventProperties, callback: ModelCallback.GetEventsCallback) {
        calendarRepo.getEvents(properties, callback)
    }

    override fun getCalendars(accountName: String, callback: ModelCallback.GetCalendarsCallback) {
        calendarRepo.getCalendars(accountName, callback)
    }

    override fun getAvailableAccounts(callback: ModelCallback.GetAccountsCallback) {
        calendarRepo.getAccounts(callback)
    }

    override fun getAccount(accountName: String, callback: ModelCallback.GetAccountCallback) {
        calendarRepo.getAccount(accountName, callback)
    }

    override fun getSavedAccount(callback: ModelCallback.GetAccountCallback) {
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