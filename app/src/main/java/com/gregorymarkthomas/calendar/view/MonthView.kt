package com.gregorymarkthomas.calendar.view

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.gregorymarkthomas.backstack.interfaces.AndroidContextInterface
import com.gregorymarkthomas.backstack.interfaces.BackStackInterface
import com.gregorymarkthomas.backstack.interfaces.ModelInterface
import com.gregorymarkthomas.backstack.view.BackStackView
import com.gregorymarkthomas.calendar.R
import com.gregorymarkthomas.calendar.databinding.MonthViewBinding
import com.gregorymarkthomas.calendar.model.AppDay
import com.gregorymarkthomas.calendar.model.CalendarModelInterface
import com.gregorymarkthomas.calendar.presenter.CalendarPresenter
import com.gregorymarkthomas.calendar.presenter.CalendarPresenterInterface
import com.gregorymarkthomas.calendar.presenter.contracts.ActivityInterface
import java.util.Date

class MonthView(private val date: Date,
                private val permissionChecker: ActivityInterface.PermissionChecker,
                private val dialogViewer: ActivityInterface.DialogViewer): BackStackView(), CalendarViewInterface, CalendarAdapter.CalendarAdapterInterface, View.OnClickListener {
    private lateinit var presenter: CalendarPresenterInterface
    private lateinit var adapter: CalendarAdapter
    private lateinit var binding: MonthViewBinding

    /********** public */
    override fun getTag(): String = "MonthView"

    override fun getLayout(): Int = R.layout.month_view

    /**
     * This is called by the PresenterInterface.
     * We used to do this in initialisePresenter(), but the view wasn't yet drawn when we were retrieving the height of the RecyclerView to determine the size of each Day view.
     * So, PresenterInterface now controls when it is created.
     * We want to show 7 days per row, so we need 7 columns for the adapter.
     */
    override fun onViewInitialised(backstack: BackStackInterface, model: ModelInterface, context: AndroidContextInterface) {
        setupTodayButton()
        setupAdapter(context)

        binding = MonthViewBinding.bind(view!!.rootView)

        /** This should be last. **/
        this.presenter = CalendarPresenter(this,
            model as CalendarModelInterface, backstack, permissionChecker, dialogViewer, date)
    }

    override fun showDates(days: List<AppDay>) {
        adapter.addAll(days)
        adapter.notifyDataSetChanged()
    }

    override fun setSelectedDateView(dayOfMonth: Int, monthOfYear: String, year: Int) {
        binding.dateTextView.text = "$monthOfYear $year"
    }

    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.viewTodayButton -> presenter.onTodayButtonPress()
        }
    }

    override fun onTimeClick(day: AppDay, hour: Int) {
        presenter.onDayPress(day, hour)
    }


    /********** private */
    private fun setupTodayButton() {
        binding.viewTodayButton.setOnClickListener(this)
    }

    private fun setupAdapter(context: AndroidContextInterface) {
        val columnCount = 7
        binding.calendarRecyclerView.layoutManager = GridLayoutManager(context.getContext(), columnCount)
        adapter = CalendarAdapter(this, binding.calendarRecyclerView.measuredHeight, columnCount,false)
        binding.calendarRecyclerView.adapter = adapter
        // TODO() - do we need this?
        binding.calendarRecyclerView.setHasFixedSize(true)
    }

}