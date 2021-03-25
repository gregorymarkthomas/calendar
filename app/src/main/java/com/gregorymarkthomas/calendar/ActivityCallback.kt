package com.gregorymarkthomas.calendar

class ActivityCallback {
    interface GetChosenAccount {
        fun onGetAccount(accountName: String)
    }
}