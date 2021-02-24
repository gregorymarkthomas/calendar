package com.gregorymarkthomas.calendar.model.interfaces

import com.gregorymarkthomas.calendar.model.AppAccount

object Option {

    interface StringOption {
        fun getData(): String?
        fun setData(data: String)
    }

    interface IntArrayOption {
        fun getData(): IntArray?
        fun setData(data: IntArray)
    }
}