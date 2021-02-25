package com.gregorymarkthomas.calendar.model.repository

import com.gregorymarkthomas.calendar.model.interfaces.Option

class AccountRepository (private val backend: BackendRepository): Option.StringOption {

    private val key = "account_key"

    override fun getData(): String? {
        return backend.getData(key, null)
    }

    override fun setData(data: String) {
        backend.setData(key, data)
    }
}