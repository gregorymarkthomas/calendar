package com.gregorymarkthomas.calendar.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialogFragment
import com.gregorymarkthomas.calendar.R


class AccountsListDialog(private val activityContext: Context): AppCompatDialogFragment() {

    private var accounts: ArrayList<String>? = null

    companion object {
        const val accountsKey = "accounts_key"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.accounts_list_dialog, container, false)

        if (arguments != null) {
            accounts = arguments!!.getStringArrayList(accountsKey)
        }

        if(accounts != null) {
            val list = view.findViewById<ListView>(R.id.list_view)
            list.adapter = AccountsListAdapter(activityContext, accounts!!)
        } else {
            throw NullPointerException()
        }

        return view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        dialog.setTitle(R.string.choose_an_account)
        return dialog
    }

    class AccountsListAdapter(context: Context, private val accounts: ArrayList<String>): BaseAdapter() {

        private val inflater: LayoutInflater = LayoutInflater.from(context)

        override fun getCount(): Int {
            return accounts.size
        }

        override fun getItem(position: Int): Any {
            return accounts[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view: View
            if(convertView == null) {
                view = inflater.inflate(R.layout.account_list_row, parent, false)
            } else {
                view = convertView
            }
            val accountNameView = view.findViewById<TextView>(R.id.account_name)
            accountNameView.text = accounts[position]
            return view
        }

    }
}