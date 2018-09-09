package com.gregorymarkthomas.calendar.model

class AppDay(val dayOfMonth: Int, val month: Int, val year: Int, val events: MutableList<AppEvent> = mutableListOf())