package br.com.zeventis.producer.domain.model.updateevent

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventUpdateRequestModel(
    val name: String? = null,
    val date: String? = null,
    val imageUrl: String? = null,
    val promotersNumber: Int? = null,
    val defaultActionsPerPromoter: Int? = null,
    val minimumActionsRequiredPerPromoter: Int? = null
) : Parcelable