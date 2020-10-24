package br.com.zeventis.producer.presentation.model.addevent

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AddEventResponsePresentation(
    val id: Long,
    val name: String,
    val date: String,
    val imageUrl: String? = null,
    val promotersNumber: Int,
    val defaultActionsPerPromoter: Int,
    val minimumActionsRequiredPerPromoter: Int,
    val eventCode: String
) : Parcelable