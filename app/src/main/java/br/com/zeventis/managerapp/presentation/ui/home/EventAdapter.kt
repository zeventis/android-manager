package br.com.zeventis.managerapp.presentation.ui.home

import android.content.Context
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zeventis.managerapp.R
import br.com.zeventis.managerapp.presentation.model.home.HomeEvent
import com.bumptech.glide.Glide
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlinx.android.synthetic.main.item_event.view.itemEventBannerIv
import kotlinx.android.synthetic.main.item_event.view.itemEventCoastTv
import kotlinx.android.synthetic.main.item_event.view.itemEventDayExtendedTv
import kotlinx.android.synthetic.main.item_event.view.itemEventDayTv
import kotlinx.android.synthetic.main.item_event.view.itemEventNameTv
import kotlinx.android.synthetic.main.item_event.view.itemEventProgressCampaignCountTv
import kotlinx.android.synthetic.main.item_event.view.itemEventProgressCampaignPb
import kotlinx.android.synthetic.main.item_event.view.itemEventPromotersNumberTv

class EventAdapter(
    private var eventsList: List<HomeEvent>? = null,
    private val context: Context,
    private val listener: EventListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = eventsList?.size!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return EventViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_event, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        bindEvent(position, holder)
    }

    private fun bindEvent(position: Int, holder: RecyclerView.ViewHolder) {
        val eventItem = eventsList?.get(position)
        val viewHolder = holder as EventViewHolder
        if (eventItem != null) {
            viewHolder.bindView(eventItem)
            holder.itemView.setOnClickListener { listener.onClickEvent(eventItem) }
        }
    }

    fun updateEventList(eventList: List<HomeEvent>) {
        this.eventsList = eventList
        notifyDataSetChanged()
    }

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(event: HomeEvent) {
            setTextFields(event)
            setImage(event.imageUrl)
        }

        private fun setTextFields(event: HomeEvent) {
            itemView.itemEventNameTv.text = event.name
            itemView.itemEventPromotersNumberTv.text = event.promotersNumber.toString()

            setProgressbar(event)
            setCoastFormatted(event)
            setDayTextView(event)
            setDayOfWeekTextView(event)
        }

        private fun setProgressbar(event: HomeEvent) {
            val eventPercentProgress = "${event.percentProgress}%"
            itemView.itemEventProgressCampaignCountTv.text = eventPercentProgress
            itemView.itemEventProgressCampaignPb.progress =
                Integer.parseInt(event.percentProgress.toString())
        }

        private fun setCoastFormatted(event: HomeEvent) {
            val ptBrLocale = Locale("pt", "BR")
            val sumTickerPriceCoastFormatted: String =
                NumberFormat.getCurrencyInstance(ptBrLocale).format(event.sumTicketPrice)
                    .replace("$", "$ ")
            itemView.itemEventCoastTv.text = sumTickerPriceCoastFormatted
        }

        private fun setDayOfWeekTextView(event: HomeEvent) {
            val calendar: Calendar = Calendar.getInstance()
            calendar.time = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "br")).parse(event.date)
            val dayOfMonth: String? =
                calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale("pt", "br"))

            itemView.itemEventDayExtendedTv.text = dayOfMonth
        }

        private fun setDayTextView(event: HomeEvent) {
            val formattedEventDate =
                SimpleDateFormat("dd/MM/yyyy", Locale("pt", "br")).parse(event.date)
            itemView.itemEventDayTv.text =
                DateFormat.format("dd", formattedEventDate).toString()
        }

        private fun setImage(imageUrl: String?) {
            if (imageUrl != null) {
                Glide.with(itemView.context)
                    .load(imageUrl)
                    .thumbnail(0.02f)
                    .into(itemView.itemEventBannerIv)
            }
        }
    }

    interface EventListener {
        fun onClickEvent(event: HomeEvent)
    }
}