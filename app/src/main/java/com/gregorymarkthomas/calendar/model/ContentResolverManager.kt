package com.gregorymarkthomas.calendar.model

import android.database.Cursor
import android.net.Uri
import android.provider.CalendarContract
import com.gregorymarkthomas.calendar.CalendarApplication

/**
 * Generic abstract class that handles the operations with the ContentResolver
 */
abstract class ContentResolverManager {

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
                null
        )
        val objectsList = convertContentIntoObjects(cursor)
        cursor.close()
        return objectsList
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