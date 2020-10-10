package br.com.zeventis.managerapp.domain.model.addevent

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AddEventRequestModel(
    val name: String,
    val date: String,
    val imageUrl: String? = null,
    val promotersNumber: Int,
    val defaultActionsPerPromoter: Int,
    val minimumActionsRequiredPerPromoter: Int
) : Parcelable