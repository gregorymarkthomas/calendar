package com.gregorymarkthomas.calendar

import android.content.ContentResolver
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.gregorymarkthomas.calendar.model.CalendarRepository
import com.gregorymarkthomas.calendar.model.Model
import com.gregorymarkthomas.calendar.util.backstack.BackStack
import com.gregorymarkthomas.calendar.util.backstack.BackStackCallback
import com.gregorymarkthomas.calendar.util.backstack.BackStackInterface
import com.gregorymarkthomas.calendar.model.interfaces.Resolver
import com.gregorymarkthomas.calendar.presenter.CalendarPermission
import com.gregorymarkthomas.calendar.presenter.contracts.AndroidPermissionContract
import com.gregorymarkthomas.calendar.util.interfaces.GetSharedPreferencesInterface
import com.gregorymarkthomas.calendar.util.interfaces.AndroidContextInterface
import com.gregorymarkthomas.calendar.view.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity: AppCompatActivity(), BackStackInterface, BackStackCallback,
        Resolver, GetSharedPreferencesInterface, AndroidContextInterface, AndroidPermissionContract {

    private val TAG = "MainActivity"
    private lateinit var backstack: BackStack

    companion object {
        const val INITIAL_VIEW_EXTRA = "initial_view_extra"
        const val MULTIPLE_PERMISSIONS = 1
    }

    /**
     * When the MainActivity view is ready, we will attach the first BackStackView.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_content.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                main_content.viewTreeObserver.removeOnGlobalLayoutListener(this)
                backstack = BackStack(this@MainActivity, getInitialView())
            }
        })


    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        backstack.goBack()
    }

    override fun getPreferences(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(this)
    }

    override fun get(): ContentResolver {
        return contentResolver
    }

    override fun getContext(): Context {
        return this
    }

    /********* BackStack - these can be called by Presenters via an interface */
    override fun goTo(view: BackStackView) {
        backstack.goTo(view)
    }

    override fun clearTo(view: BackStackView) {
        backstack.clearTo(view)
    }

    override fun goBack(): Boolean {
        return backstack.goBack()
    }

    override fun getMostRecentView(): BackStackView {
        return backstack.getMostRecentView()
    }

    override fun getCurrentViewClasses(): List<String> {
        return backstack.getCurrentViewClasses()
    }

    /**
     * When the BackStackView has been added to MainActivity, we can say it has been initialised.
     */
    override fun onViewChanged(backstackView: BackStackView) {
        main_content.removeAllViews()
        val view = backstackView.initialise(this)

        val listener = object: ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                view.viewTreeObserver.removeOnGlobalLayoutListener(this)
                backstackView.onInitialised(this@MainActivity,
                        Model(CalendarRepository(this@MainActivity), this@MainActivity),
                        this@MainActivity
                )
            }
        }

        view.viewTreeObserver.addOnGlobalLayoutListener(listener)
        main_content.addView(view, main_content.width, main_content.height)
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

    /**
     * If request is cancelled, the result arrays are empty.
     */
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MULTIPLE_PERMISSIONS -> {
                if ((grantResults.isNotEmpty() &&
                                grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    recreate()
                } else {
                    backstack.clearTo(PermissionDeniedView())
                }
                return
            }
            else -> {
                // Ignore all other requests.
            }
        }
    }


    /********** private */
    private fun getInitialView(): BackStackView {
        val viewClass: Class<out BackStackView>? = intent.getSerializableExtra(INITIAL_VIEW_EXTRA) as Class<out BackStackView>?
        val today = Date()
        return when (viewClass) {
            DayView::class.java -> DayView(today)
            WeekView::class.java -> WeekView(today)
            else -> MonthView(today, this@MainActivity)
        }
    }
}