package br.com.zeventis.managerapp.presentation.ui.home

import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zeventis.managerapp.R
import br.com.zeventis.managerapp.core.network.SessionManager
import br.com.zeventis.managerapp.core.plataform.BaseFragment
import br.com.zeventis.managerapp.presentation.model.home.HomeEvent
import br.com.zeventis.managerapp.presentation.model.home.HomeEvents
import java.math.BigDecimal
import kotlinx.android.synthetic.main.fragment_home.homeFragmentEventsListRv
import kotlinx.android.synthetic.main.fragment_home.homeFragmentUserTv
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment(), EventAdapter.EventListener {

    private val homeViewModel: HomeViewModel by inject()
    private val sessionManager: SessionManager by inject()

    private var eventsAdapter: EventsAdapter? = null
    private var mockEventsList = listOf(
        HomeEvents(
            "DEZ/21", listOf(
                HomeEvent(
                    1,
                    "Universo Paralelo",
                    "26/12/2021",
                    "URL",
                    BigDecimal(200.00),
                    67,
                    100
                ),
                HomeEvent(
                    2,
                    "OHM",
                    "25/12/2021",
                    "URL",
                    BigDecimal(180.00),
                    20,
                    12
                )
            )
        ), HomeEvents(
            "MAI/20", listOf(
                HomeEvent(
                    3,
                    "Rifaina Beach Festival",
                    "11/05/2020",
                    "DEZ/21",
                    BigDecimal(150),
                    99,
                    10
                ),
            )
        )
    )

    override fun getContentLayoutId(): Int = R.layout.fragment_home

    override fun init() {
        observeViewModelEvents()
        initUserLoggedText()
        initAdapter()
        //        homeViewModel.getEvents() TODO Remove comment when service is ready
    }

    override fun onClickEvent(event: HomeEvent) {
        // TODO Implements click event redirect to event detail
    }

    private fun initAdapter() {
        eventsAdapter = activity?.let {
            EventsAdapter(
                eventsList = mockEventsList,
                context = it,
                listener = this@HomeFragment,
                activity as HomeActivity?
            )
        }

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        homeFragmentEventsListRv.layoutManager = layoutManager
        homeFragmentEventsListRv.adapter = eventsAdapter
    }


    private fun observeViewModelEvents() {
        homeViewModel.viewState.observe(viewLifecycleOwner, {
            when (it) {
                is HomeViewEvents.OnGetEventsSuccess -> handleGetEventSuccess(it.eventsList)
                is HomeViewEvents.OnGetEventsFailed -> handleError(
                    HomeFragment::class.java.toString(),
                    it.exceptionError
                )
            }
        })
    }

    private fun handleGetEventSuccess(eventsList: List<HomeEvents>) {
        eventsAdapter?.updateEventList(eventsList)
    }

    private fun initUserLoggedText() {
        homeFragmentUserTv.text =
            getString(R.string.home_user_logged, sessionManager.getTwoFirstNameUser())
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}