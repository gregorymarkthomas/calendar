package com.gregorymarkthomas.calendar.model.repository

interface BackendRepository {
    fun getData(key: String, defaultGetValue: String?): String?
    fun setData(key: String, str: String)
}