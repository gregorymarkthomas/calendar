package com.gregorymarkthomas.calendar.model

import android.database.Cursor
import android.net.Uri
import android.provider.CalendarContract.Calendars
import com.gregorymarkthomas.calendar.model.interfaces.Resolver
import com.gregorymarkthomas.calendar.util.CursorExtractor

class AccountRetriever(private val resolver: Resolver): CalendarResolverQuery(resolver) {

    /************* public *****/
    fun getAvailable(): List<AppAccount> {
        val cursor = query(null, null)
        var accounts: List<AppAccount> = listOf()
        if(cursor != null) {
            accounts = createAccounts(cursor)
            cursor.close()
        }
        return accounts
    }

    fun get(accountName: String): AppAccount? {
        val cursor = query(accountName, null)
        var account: AppAccount? = null
        if(cursor != null) {
            account = AppAccount(
                    CursorExtractor.Calendar.getAccountName(cursor),
                    CursorExtractor.Calendar.getOwnerAccount(cursor),
                    "com.google"
            )
            cursor.close()
        }
        return account
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
    /** TODO: currently tied down to Google Calendar accounts **/
    private fun createAccounts(cursor: Cursor): List<AppAccount> {
        val accounts = mutableListOf<AppAccount>()
        while(cursor.moveToNext()) {
            accounts.add(AppAccount(
                    CursorExtractor.Calendar.getAccountName(cursor),
                    CursorExtractor.Calendar.getOwnerAccount(cursor),
                    "com.google"
            ))
        }
        return accounts
    }
}