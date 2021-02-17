package com.gregorymarkthomas.calendar.model

import android.database.Cursor
import android.net.Uri
import android.provider.CalendarContract.Calendars
import com.gregorymarkthomas.calendar.model.interfaces.Resolver
import com.gregorymarkthomas.calendar.util.CursorExtractor

class AccountRetriever(private val resolver: Resolver): CalendarResolverQuery(resolver) {

    /************* public *****/
    fun get(): List<AppAccount> {
        val cursor = query(null)
        var accounts: List<AppAccount> = listOf()
        if(cursor != null) {
            accounts = createAccounts(cursor)
            cursor.close()
        }
        return accounts
    }

    /************* protected *****/
    override fun getUri(): Uri {
        return Calendars.CONTENT_URI
    }

    override fun getFields(): Array<String> {
        return arrayOf(
                Calendars.ACCOUNT_NAME,
                Calendars.OWNER_ACCOUNT)
    }

    /**
     * The sort order does not matter, hence null.
     */
    override fun getSortOrder(): String? {
        return null
    }


    /************* private *****/
    private fun createAccounts(cursor: Cursor): List<AppAccount> {
        val accounts = mutableListOf<AppAccount>()
        while(cursor.moveToNext()) {
            accounts.add(AppAccount(
                    CursorExtractor.Calendar.getAccountName(cursor),
                    CursorExtractor.Calendar.getOwnerAccount(cursor)
            ))
        }
        return accounts
    }
}