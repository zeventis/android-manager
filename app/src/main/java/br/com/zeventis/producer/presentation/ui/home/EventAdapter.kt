package br.com.zeventis.producer.presentation.ui.home

import android.content.Context
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zeventis.producer.R
import br.com.zeventis.producer.presentation.model.home.HomeEvent
import com.bumptech.glide.Glide
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlinx.android.synthetic.main.item_event.view.itemEventBannerIv
import kotlinx.android.synthetic.main.item_event.view.itemEventCoastTv
import kotlinx.android.synthetic.main.item_event.view.itemEventDayExtendedTv
import kotlinx.android.synthetic.main.item_event.view.itemEventDayTv
import kotlinx.android.synthetic.main.item_event.view.itemEventHourTv
import kotlinx.android.synthetic.main.item_event.view.itemEventNameTv
import kotlinx.android.synthetic.main.item_event.view.itemEventProgressCampaignCountTv
import kotlinx.android.synthetic.main.item_event.view.itemEventProgressCampaignPb
import kotlinx.android.synthetic.main.item_event.view.itemEventPromotersNumberTv

class EventAdapter(
    private val eventsList: List<HomeEvent>,
    private val context: Context,
    private val listener: EventListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = eventsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return EventViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_event, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        bindEvent(position, holder)
    }

    private fun bindEvent(position: Int, holder: RecyclerView.ViewHolder) {
        val eventItem = eventsList[position]
        val viewHolder = holder as EventViewHolder
        viewHolder.bindView(eventItem)
        holder.itemView.setOnClickListener { listener.onClickEvent(eventItem) }
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
            setHourTextView(event)
        }

        private fun setHourTextView(event: HomeEvent) {
            val hour = event.date.split("T")[1]
            itemView.itemEventHourTv.text = hour
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
            val dateWithoutHour = event.date.split("T")[0]
            calendar.time = SimpleDateFormat("yyyy-MM-dd", Locale("pt", "br")).parse(dateWithoutHour)
            val dayOfMonth: String? =
                calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale("pt", "br"))

            itemView.itemEventDayExtendedTv.text = dayOfMonth
        }

        private fun setDayTextView(event: HomeEvent) {
            val dateWithoutHour = event.date.split("T")[0]
            val formattedEventDate =
                SimpleDateFormat("yyyy-MM-dd", Locale("pt", "br")).parse(dateWithoutHour)
            itemView.itemEventDayTv.text =
                DateFormat.format("dd", formattedEventDate).toString()
        }

        private fun setImage(imageUrl: String?) {
            if (imageUrl != null) {
                Glide.with(itemView.context)
                    .load(imageUrl)
                    .thumbnail(0.005f)
                    .into(itemView.itemEventBannerIv)
            }
        }
    }

    interface EventListener {
        fun onClickEvent(event: HomeEvent)
    }
}