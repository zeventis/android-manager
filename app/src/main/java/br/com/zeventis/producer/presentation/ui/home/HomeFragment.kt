package br.com.zeventis.producer.presentation.ui.home

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zeventis.producer.R
import br.com.zeventis.producer.core.network.SessionManager
import br.com.zeventis.producer.core.plataform.BaseFragment
import br.com.zeventis.producer.presentation.model.home.HomeEvent
import br.com.zeventis.producer.presentation.model.home.HomeEvents
import br.com.zeventis.producer.presentation.ui.addevent.AddEventActivity
import kotlinx.android.synthetic.main.fragment_home.homeFragmentAddEventBt
import kotlinx.android.synthetic.main.fragment_home.homeFragmentEventsListRv
import kotlinx.android.synthetic.main.fragment_home.homeFragmentUserTv
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment(), EventAdapter.EventListener {

    private val homeViewModel: HomeViewModel by inject()
    private val sessionManager: SessionManager by inject()

    private var eventsAdapter: EventsAdapter? = null

    override fun getContentLayoutId(): Int = R.layout.fragment_home

    override fun init() {
        observeViewModelEvents()
        initUserLoggedText()
        initAdapter()
        initClickListeners()
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.getEvents()
    }

    override fun onClickEvent(event: HomeEvent) {
        // TODO Implements click event redirect to event detail
    }

    private fun initAdapter() {
        eventsAdapter = activity?.let {
            EventsAdapter(
                context = it,
                listener = this@HomeFragment,
                activity = activity as HomeActivity?
            )
        }

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        homeFragmentEventsListRv.layoutManager = layoutManager
        homeFragmentEventsListRv.adapter = eventsAdapter
    }

    private fun initClickListeners() {
        homeFragmentAddEventBt.setOnClickListener {
            startActivity(Intent(activity, AddEventActivity::class.java))
        }
    }

    private fun observeViewModelEvents() {
        homeViewModel.viewState.observe(viewLifecycleOwner, {
            when (it) {
                is HomeViewEvents.OnGetEventsSuccess -> handleGetEventSuccess(it.eventsList)
                is HomeViewEvents.OnGetEventsFailed -> handleError(
                    this::class.java.toString(),
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