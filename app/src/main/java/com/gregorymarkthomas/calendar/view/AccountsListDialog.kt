package com.gregorymarkthomas.calendar.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.gregorymarkthomas.calendar.R
import com.gregorymarkthomas.calendar.model.AppAccount

class AccountsListDialog(private val activityContext: Context): DialogFragment() {

    companion object {
        const val accountsKey = "accounts_key"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.accounts_list_dialog, container)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var accounts: ArrayList<String>? = null
        if (arguments != null) {
            accounts = arguments!!.getStringArrayList(accountsKey)
        }

        if(accounts != null) {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}