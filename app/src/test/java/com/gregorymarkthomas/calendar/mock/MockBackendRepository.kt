package com.gregorymarkthomas.calendar.mock

import com.gregorymarkthomas.calendar.model.repository.BackendRepository

class MockBackendRepository(): BackendRepository {
    private var data: String? = null

    override fun getData(key: String, defaultGetValue: String?): String? {
        return data
    }

    override fun setData(key: String, str: String) {
        data = str
    }
}