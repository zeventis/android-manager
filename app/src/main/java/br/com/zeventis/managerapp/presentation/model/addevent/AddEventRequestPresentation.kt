package br.com.zeventis.managerapp.presentation.model.addevent

import android.os.Parcelable
import java.time.LocalDateTime
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AddEventRequestPresentation(
    val name: String,
    val date: String,
    val base64Image: String? = null,
    val defaultActionsPerPromoter: Int,
    val minimumActionsRequiredPerPromoter: Int
) : Parcelable