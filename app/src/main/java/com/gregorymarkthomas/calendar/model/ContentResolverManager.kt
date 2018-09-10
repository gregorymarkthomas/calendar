package com.gregorymarkthomas.calendar.model

import android.database.Cursor
import android.net.Uri
import android.provider.CalendarContract
import com.gregorymarkthomas.calendar.CalendarApplication
import java.util.*

/**
 * Generic abstract class that handles the operations with the ContentResolver
 */
abstract class ContentResolverManager {

    protected val ASCENDING = " ASC"
    protected val DESCENDING = " DESC"
    protected val AND = " AND "
    protected val OR = " OR "
    protected val GREATER_THAN = " > "
    protected val GREATER_THAN_OR_EQUAL = " >= "
    protected val LESS_THAN = " < "
    protected val LESS_THAN_OR_EQUAL = " <= "
    protected val EQUALS = " = "

    private val resolver = CalendarApplication.applicationContext.contentResolver

    /************* public *****/


    /************* protected *****/
    protected abstract fun getUri(): Uri
    protected abstract fun getFields(): Array<String>
    protected abstract fun getSortOrder(): String?

    /**
     * Converts single data row into object
     */
    protected abstract fun getObjectFromRow(cursor: Cursor): Any

    /**
     * This must be called by child classes
     */
    protected fun get(whereClauses: String?): MutableList<Any> {
        var finalWhereClause = getDefaultWhereClause()

        if(whereClauses != null) {
            finalWhereClause = mergeWhereClause(finalWhereClause, whereClauses)
        }

        val cursor = this.resolver.query(
                getUri(),
                getFields(),
                finalWhereClause,
                null,
                getSortOrder()
        )
        val objectsList = convertContentIntoObjects(cursor)
        cursor.close()
        return objectsList
    }

    /**
     * We set 'leniency' to true so that if day=32 and month=0, then February the 1st will be output, instead of an exception being thrown.
     */
    protected fun getEpoch(day: Int, month: Int, year: Int): Long {
        val timeZone = TimeZone.getTimeZone("UTC")
        val calendar = Calendar.getInstance(timeZone)
        calendar.isLenient = true
        calendar.set(year, month, day)
        return calendar.timeInMillis
    }


    /************* private *****/
    /**
     * TODO - remove hardcoded account
     */
    private fun getDefaultWhereClause(): String {
        val where = "((" + CalendarContract.Calendars.ACCOUNT_NAME + " = 'hello@hello.com') AND (" + CalendarContract.Calendars.ACCOUNT_TYPE + " = 'com.google') AND (" + CalendarContract.Calendars.OWNER_ACCOUNT + " = 'hello@hello.com'))"
        return where
    }

    private fun mergeWhereClause(where1: String, where2: String): String {
        return where1 + where2
    }

    private fun convertContentIntoObjects(cursor: Cursor): MutableList<Any> {
        val objs = mutableListOf<Any>()
        while(cursor.moveToNext()) {
            objs.add(getObjectFromRow(cursor))
        }
        return objs
    }
}