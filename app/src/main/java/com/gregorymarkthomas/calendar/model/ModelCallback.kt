package com.gregorymarkthomas.calendar.model

class ModelCallback {
    interface GetCalendarsCallback {
        fun onGetCalendars(calendars: List<AppCalendar>)
    }
    interface GetEventsCallback {
        fun onGetEvents(days: List<AppDay>)
    }
    interface GetAccountsCallback {
        fun onGetAccounts(accounts: List<AppAccount>)
    }
    interface GetAccountCallback {
        fun onGetAccount(account: AppAccount?)
    }
}