package com.gregorymarkthomas.calendar.model

import com.gregorymarkthomas.calendar.model.interfaces.Option
import com.gregorymarkthomas.calendar.util.interfaces.GetSharedPreferencesInterface

class StringSharedPreferenceOptionData(private val preferences: GetSharedPreferencesInterface, private val key: String, private val defaultGetValue: String): Option.StringOption {
    override fun getData(): String? {
        return preferences.getPreferences().getString(key, defaultGetValue)
    }

    override fun setData(str: String) {
        preferences.getPreferences().edit().putString(key, str).apply()
    }
}
