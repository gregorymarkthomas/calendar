package com.gregorymarkthomas.calendar.model.interfaces

import android.content.ContentResolver

interface Resolver {
    fun get(): ContentResolver
}