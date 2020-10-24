package br.com.zeventis.producer.domain.model.home

import android.os.Parcelable
import java.math.BigDecimal
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HomeEventsModel(
    val date: String,
    val events: List<HomeEventModel>
) : Parcelable

@Parcelize
data class HomeEventModel(
    val id: Long,
    val name: String,
    val date: String,
    val imageUrl: String,
    val sumTicketPrice: BigDecimal,
    val percentProgress: Int,
    val promotersNumber: Int
) : Parcelable