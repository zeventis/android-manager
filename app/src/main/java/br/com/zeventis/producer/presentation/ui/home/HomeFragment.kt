package br.com.zeventis.producer.presentation.ui.home

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zeventis.producer.R
import br.com.zeventis.producer.core.network.SessionManager
import br.com.zeventis.producer.core.plataform.BaseFragment
import br.com.zeventis.producer.core.plataform.DefaultViewState
import br.com.zeventis.producer.presentation.model.home.HomeEvent
import br.com.zeventis.producer.presentation.model.home.HomeEvents
import br.com.zeventis.producer.presentation.ui.addevent.AddEventActivity
import kotlinx.android.synthetic.main.fragment_home.homeFragmentAddEventBt
import kotlinx.android.synthetic.main.fragment_home.homeFragmentEmptyIv
import kotlinx.android.synthetic.main.fragment_home.homeFragmentEmptyTv
import kotlinx.android.synthetic.main.fragment_home.homeFragmentEventsListRv
import kotlinx.android.synthetic.main.fragment_home.homeFragmentEventsListShimmerSfl
import kotlinx.android.synthetic.main.fragment_home.homeFragmentUserTv
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment(), EventAdapter.EventListener {

    private val homeViewModel: HomeViewModel by inject()
    private val sessionManager: SessionManager by inject()

    private var eventsAdapter: EventsAdapter? = null

    override fun getContentLayoutId(): Int = R.layout.fragment_home

    override fun init() {
        observeViewModelEvents()
        observeViewModelStates()
        initUserLoggedTextView()
        initAdapter()
        initClickListeners()
    }

    override fun onResume() {
        super.onResume()
        hideEmptyListEventsView()
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
        homeViewModel.viewEvents.observe(viewLifecycleOwner, {
            when (it) {
                is HomeViewEvents.OnGetEventsSuccess -> handleGetEventSuccess(it.eventsList)
                is HomeViewEvents.OnGetEventsEmpty -> handleGetEventsEmpty()
                is HomeViewEvents.OnGetEventsFailed -> handleError(this::class.java.toString(), it.exceptionError)
            }
        })
    }

    private fun observeViewModelStates() {
        homeViewModel.viewState.observe(viewLifecycleOwner, {
            when (it) {
                is DefaultViewState.ShowLoading -> startLoading()
                is DefaultViewState.HideLoading -> hideLoading()
            }
        })
    }

    private fun handleGetEventsEmpty() {
        clearEvents()
        homeFragmentEventsListRv.visibility = View.GONE
        homeFragmentEmptyTv.visibility = View.VISIBLE
        homeFragmentEmptyIv.visibility = View.VISIBLE
    }

    private fun startLoading() {
        homeFragmentEventsListRv.visibility = View.GONE
        homeFragmentEventsListShimmerSfl.visibility = View.VISIBLE
        homeFragmentEventsListShimmerSfl.startShimmer()
    }

    private fun hideLoading() {
        homeFragmentEventsListRv.visibility = View.VISIBLE
        homeFragmentEventsListShimmerSfl.visibility = View.GONE
        homeFragmentEventsListShimmerSfl.stopShimmer()
    }

    private fun handleGetEventSuccess(eventsList: List<HomeEvents>) {
        hideEmptyListEventsView()
        eventsAdapter?.updateEventList(eventsList)
    }

    private fun clearEvents() {
        eventsAdapter?.clearEventList()
    }

    private fun hideEmptyListEventsView() {
        homeFragmentEmptyTv.visibility = View.GONE
        homeFragmentEmptyIv.visibility = View.GONE
    }

    private fun initUserLoggedTextView() {
        homeFragmentUserTv.text =
            getString(R.string.home_user_logged, sessionManager.getTwoFirstNameUser())
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}