package com.gregorymarkthomas.calendar.model.repository.sharedpreferences

import com.gregorymarkthomas.calendar.model.repository.AccountRepository
import com.gregorymarkthomas.calendar.model.AppAccount
import com.gregorymarkthomas.calendar.util.interfaces.GetSharedPreferencesInterface

class SharedPreferencesAccountRepository(preferences: GetSharedPreferencesInterface): AccountRepository {
    override fun getAccount(): AppAccount {
        TODO("Not yet implemented")
    }

    override fun setAccount(account: AppAccount) {
        TODO("Not yet implemented")
    }
}