package com.gregorymarkthomas.calendar.model.repository

import com.gregorymarkthomas.calendar.util.interfaces.GetSharedPreferencesInterface

class SharedPrefsBackendRepository(private val preferences: GetSharedPreferencesInterface): BackendRepository {
    override fun getData(key: String, defaultGetValue: String): String? {
        return preferences.getPreferences().getString(key, defaultGetValue)
    }

    override fun setData(key: String, str: String) {
        preferences.getPreferences().edit().putString(key, str).apply()
    }
}