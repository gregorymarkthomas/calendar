package com.gregorymarkthomas.calendar.model.interfaces

object Option {

    interface StringOption {
        fun getData(): String?
        fun setData(str: String)
    }
}