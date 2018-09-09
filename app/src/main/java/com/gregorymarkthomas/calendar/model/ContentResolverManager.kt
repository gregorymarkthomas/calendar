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
    protected fun get(whereClauses: String?, whereArgs: Array<String>?): MutableList<Any> {
        var (finalWhereClause, finalWhereArgs) = getDefaultWhereClause()

        if(whereClauses != null && whereArgs != null) {
            finalWhereClause = mergeWhereClause(finalWhereClause, whereClauses)
            finalWhereArgs = mergeWhereArgs(finalWhereArgs, whereArgs)
        }

        val cursor = this.resolver.query(
                getUri(),
                getFields(),
                finalWhereClause,
                finalWhereArgs,
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
    private fun getDefaultWhereClause(): Pair<String, Array<String>> {
        val where = "((" + CalendarContract.Calendars.ACCOUNT_NAME + " = ?) AND (" + CalendarContract.Calendars.ACCOUNT_TYPE + " = ?) AND (" + CalendarContract.Calendars.OWNER_ACCOUNT + " = ?))"
        val args = arrayOf("hello@hello.com", "com.google", "hello@hello.com")
        return Pair(where, args)
    }

    private fun mergeWhereClause(where1: String, where2: String): String {
        return where1 + where2
    }

    private fun mergeWhereArgs(args1: Array<String>, args2: Array<String>): Array<String> {
        return args1.plus(args2)
    }

    private fun convertContentIntoObjects(cursor: Cursor): MutableList<Any> {
        val objs = mutableListOf<Any>()
        while(cursor.moveToNext()) {
            objs.add(getObjectFromRow(cursor))
        }
        return objs
    }
}