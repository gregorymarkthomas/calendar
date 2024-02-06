package com.gregorymarkthomas.calendar

import android.content.ContentResolver
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.gregorymarkthomas.backstack.interfaces.ModelInterface
import com.gregorymarkthomas.backstack.view.BackStackView
import com.gregorymarkthomas.backstack.view.BackstackActivity
import com.gregorymarkthomas.calendar.databinding.ActivityMainBinding
import com.gregorymarkthomas.calendar.model.Model
import com.gregorymarkthomas.calendar.model.interfaces.Resolver
import com.gregorymarkthomas.calendar.model.repository.AccountRepository
import com.gregorymarkthomas.calendar.model.repository.AndroidCalendarRepository
import com.gregorymarkthomas.calendar.model.repository.CalendarVisibilityRepository
import com.gregorymarkthomas.calendar.model.repository.SharedPrefsBackendRepository
import com.gregorymarkthomas.calendar.presenter.CalendarPermission
import com.gregorymarkthomas.calendar.presenter.contracts.ActivityInterface
import com.gregorymarkthomas.calendar.util.interfaces.GetSharedPreferencesInterface
import com.gregorymarkthomas.calendar.view.DayView
import com.gregorymarkthomas.calendar.view.MonthView
import com.gregorymarkthomas.calendar.view.NoAccountsDialog
import com.gregorymarkthomas.calendar.view.PermissionDeniedView
import com.gregorymarkthomas.calendar.view.WeekView
import java.util.Date

class MainActivity: BackstackActivity(), Resolver, GetSharedPreferencesInterface,
    ActivityInterface.PermissionChecker, ActivityInterface.DialogViewer {

    private val tag = "MainActivity"
    private lateinit var model: ModelInterface
    private lateinit var binding: ActivityMainBinding

    companion object {
        const val MULTIPLE_PERMISSIONS = 1
        const val accountsListDialogTag = "accounts_list_dialog_tag"
        const val noAccountsDialogTag = "no_accounts_dialog_tag"
    }

    /**
     * Call to super.onCreate() is supposed to be at end.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model = Model(AndroidCalendarRepository(this@MainActivity),
            AccountRepository(SharedPrefsBackendRepository(this@MainActivity)),
            CalendarVisibilityRepository(SharedPrefsBackendRepository(this@MainActivity))
        )
        super.onCreate(savedInstanceState)
    }

    override fun getInitialView(): BackStackView {
        val viewClass: Class<BackStackView>? = intent.serializable(INITIAL_VIEW_EXTRA)
        val today = Date()
        return when (viewClass) {
            DayView::class.java -> DayView(today)
            WeekView::class.java -> WeekView(today)
            else -> MonthView(today, this@MainActivity, this@MainActivity)
        }
    }

    override fun addView(view: ConstraintLayout) {
        binding.root.addView(view)
    }

    override fun removeAllViews() {
        binding.root.removeAllViews()
    }

    override fun getModel(): ModelInterface {
        return model
    }

    override fun getPreferences(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(this)
    }

    override fun get(): ContentResolver {
        return contentResolver
    }

    override fun isPermissionGranted(permission: CalendarPermission): Boolean {
        val permissionStatus = ContextCompat.checkSelfPermission(this, permission.permission)
        return permissionStatus == PackageManager.PERMISSION_GRANTED
    }

    override fun showPermissionDialog(permissions: List<CalendarPermission>) {
        val p = mutableListOf<String>()
        for(permission in permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission.permission)) {
                Toast.makeText(this, permission.permission + " is required for this app to run", Toast.LENGTH_LONG).show();
            }
            p.add(permission.permission)
        }

        ActivityCompat.requestPermissions(this, p.toTypedArray(), MULTIPLE_PERMISSIONS)
    }

    override fun showAccountsDialog(accounts: Array<String>, callback: ActivityCallback.GetChosenAccount) {
        val checkedItem = 0
        MaterialAlertDialogBuilder(this)
            .setTitle(resources.getString(R.string.choose_an_account))
            .setPositiveButton(resources.getString(R.string.okay)) { dialog, which ->
                val lw: ListView = (dialog as AlertDialog).listView
                val checkedItem: String = lw.adapter.getItem(lw.checkedItemPosition) as String
                callback.onGetAccount(checkedItem)
            }
            .setSingleChoiceItems(accounts, checkedItem) { dialog, which ->
                // Do nothing
            }
            .show()
    }

    override fun showNoAccountsDialog() {
        val dialog = NoAccountsDialog()
        dialog.show(supportFragmentManager, noAccountsDialogTag)
    }

    /**
     * If request is cancelled, the result arrays are empty.
     */
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MULTIPLE_PERMISSIONS -> {
                if ((grantResults.isNotEmpty() &&
                                grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    recreate()
                } else {
                    clearTo(PermissionDeniedView())
                }
                return
            }
            else -> {
                // Ignore all other requests.
            }
        }
    }
}