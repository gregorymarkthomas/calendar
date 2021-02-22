package com.gregorymarkthomas.calendar.model.repository

import com.gregorymarkthomas.calendar.model.AppAccount

interface AccountRepository {
    fun getAccount(): AppAccount
    fun setAccount(account: AppAccount)
}