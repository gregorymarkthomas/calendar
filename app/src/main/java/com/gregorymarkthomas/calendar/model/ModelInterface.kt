package com.gregorymarkthomas.calendar.model

import java.util.*

interface ModelInterface {
    fun getTodayDate(): Date
    fun getEvents(accountName: String, dayInMonth: Int, month: Int, year: Int, numberOfDays: Int, callback: ModelCallback.GetEventsCallback, calendarsToShow: IntArray = IntArray(0))
    fun getCalendars(accountName: String, callback: ModelCallback.GetCalendarsCallback)
    fun getAvailableAccounts(callback: ModelCallback.GetAccountsCallback)
    fun getAccount(accountName: String, callback: ModelCallback.GetAccountCallback)
    fun getSavedAccount(callback: ModelCallback.GetAccountCallback)
    fun setAccount(accountName: String)
    fun getVisibleCalendars(): IntArray
    fun setVisibleCalendars(calendarsToShow: IntArray)
}