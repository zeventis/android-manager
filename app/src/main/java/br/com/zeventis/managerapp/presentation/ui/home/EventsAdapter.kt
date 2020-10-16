package br.com.zeventis.managerapp.presentation.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.zeventis.managerapp.R
import br.com.zeventis.managerapp.presentation.model.home.HomeEvent
import br.com.zeventis.managerapp.presentation.model.home.HomeEvents
import kotlinx.android.synthetic.main.item_events.view.itemEventsEventCountEventTv
import kotlinx.android.synthetic.main.item_events.view.itemEventsEventMonthTv
import kotlinx.android.synthetic.main.item_events.view.itemEventsListEventRv

class EventsAdapter(
    private var eventsList: List<HomeEvents>? = null,
    private val context: Context,
    private val listener: EventAdapter.EventListener,
    private val activity: HomeActivity?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = if (eventsList.isNullOrEmpty()) {
        0
    } else {
        eventsList!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HeaderViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_events, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        bindEvents(position, holder)
    }

    private fun bindEvents(position: Int, holder: RecyclerView.ViewHolder) {
        val events = eventsList?.get(position)
        val viewHolder = holder as HeaderViewHolder

        if (eventsList != null && events != null) {
            viewHolder.bindView(
                events.date,
                events.events,
                context,
                listener,
                activity
            )
        }
    }

    fun updateEventList(eventList: List<HomeEvents>) {
        this.eventsList = eventList
        notifyDataSetChanged()
    }

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(
            monthExtended: String,
            eventList: List<HomeEvent>,
            context: Context,
            listener: EventAdapter.EventListener,
            activity: HomeActivity?
        ) {
            itemView.itemEventsEventCountEventTv.text =
                context.getString(R.string.home_event_count, eventList.size)
            itemView.itemEventsEventMonthTv.text = monthExtended

            setEventsAdapter(activity, eventList, context, listener)
        }

        private fun setEventsAdapter(
            activity: HomeActivity?,
            eventList: List<HomeEvent>,
            context: Context,
            listener: EventAdapter.EventListener
        ) {
            val eventsAdapter: EventAdapter? = activity?.let {
                EventAdapter(
                    eventsList = eventList,
                    context = context,
                    listener = listener
                )
            }

            val layoutManager = LinearLayoutManager(activity)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            itemView.itemEventsListEventRv.layoutManager = layoutManager
            itemView.itemEventsListEventRv.adapter = eventsAdapter
        }
    }

}