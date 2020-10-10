package br.com.zeventis.managerapp.domain.model.eventdetail

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventDetailModel(
    val id: Long,
    val name: String,
    val date: String,
    val imageUrl: String? = null,
    val promotersNumber: Int,
    val defaultActionsPerPromoter: Int,
    val minimumActionsRequiredPerPromoter: Int,
    val eventCode: String
) : Parcelable