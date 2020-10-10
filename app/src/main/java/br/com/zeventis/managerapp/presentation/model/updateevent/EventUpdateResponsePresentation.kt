package br.com.zeventis.managerapp.presentation.model.updateevent

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventUpdateResponsePresentation(
    val id: Long,
    val name: String,
    val date: String,
    val imageUrl: String,
    val promotersNumber: Int,
    val defaultActionsPerPromoter: Int,
    val minimumActionsRequiredPerPromoter: Int,
    val eventCode: String
) : Parcelable