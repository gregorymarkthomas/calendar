package com.gregorymarkthomas.calendar.model.interfaces

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