package com.gregorymarkthomas.calendar.model.interfaces

object Option {

    /**
     * Data is stored as a comma-separated-String
     */
    interface IntArrayOption {
        fun getData(): String?
        fun setData(commaSeparatedStr: String)
    }
}