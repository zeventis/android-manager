package br.com.zeventis.managerapp.domain.model.addevent

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AddEventResponseModel(
    val id: Long,
    val name: String,
    val date: String,
    val imageUrl: String? = null,
    val promotersNumber: Int,
    val defaultActionsPerPromoter: Int,
    val minimumActionsRequiredPerPromoter: Int,
    val eventCode: String
) : Parcelable