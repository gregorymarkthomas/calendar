package com.gregorymarkthomas.calendar.util.interfaces

import android.content.SharedPreferences

interface GetSharedPreferencesInterface {
    fun getPreferences(): SharedPreferences
}