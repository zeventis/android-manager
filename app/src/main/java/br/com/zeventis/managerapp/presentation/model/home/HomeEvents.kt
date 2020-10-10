package br.com.zeventis.managerapp.presentation.model.home

import android.os.Parcelable
import java.math.BigDecimal
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HomeEvents(
    val date: String,
    val events: List<HomeEvent>
) : Parcelable

@Parcelize
data class HomeEvent(
    val id: Long,
    val name: String,
    val date: String,
    val imageUrl: String,
    val sumTicketPrice: BigDecimal,
    val percentProgress: Int,
    val promotersNumber: Int
) : Parcelable