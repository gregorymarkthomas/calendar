package com.gregorymarkthomas.calendar.model

import android.database.Cursor
import android.net.Uri
import android.provider.CalendarContract
import com.gregorymarkthomas.calendar.model.interfaces.Resolver
import java.util.*

/**
 * Generic abstract class that handles the operations with the ContentResolver
 */
abstract class CalendarResolverQuery(private val resolver: Resolver) {

    /************* protected *****/
    protected abstract fun getUri(): Uri
    protected abstract fun getFields(): Array<String>
    protected abstract fun getSortOrder(): String?

    fun query(accountName: String?, whereClauses: String?): Cursor? {
        var finalWhereClause = ""
        if(accountName != null) {
            finalWhereClause = generateAccountWhereClause(accountName)
        }
        if(whereClauses != null) {
            finalWhereClause = mergeWhereClause(finalWhereClause, whereClauses)
        }
        return this.resolver.get().query(
            getUri(),
            getFields(),
            finalWhereClause,
            null,
            getSortOrder()
        )
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
    private fun generateAccountWhereClause(accountName: String): String {
        return CalendarContract.Calendars.ACCOUNT_NAME + " = '$accountName'"
    }

    private fun mergeWhereClause(where1: String, where2: String): String {
        return where1 + where2
    }

}