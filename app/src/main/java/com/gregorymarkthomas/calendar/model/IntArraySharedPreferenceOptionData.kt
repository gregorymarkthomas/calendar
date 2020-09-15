package com.gregorymarkthomas.calendar.model

import com.gregorymarkthomas.calendar.model.interfaces.Option
import com.gregorymarkthomas.calendar.util.interfaces.GetSharedPreferencesInterface

class IntArraySharedPreferenceOptionData(private val preferences: GetSharedPreferencesInterface, private val key: String, private val defaultGetValue: String): Option.IntArrayOption {
    override fun getData(): String {
        return preferences.getPreferences().getString(key, defaultGetValue)
    }

    override fun setData(commaSeparatedStr: String) {
        preferences.getPreferences().edit().putString(key, commaSeparatedStr).apply()
    }
}
