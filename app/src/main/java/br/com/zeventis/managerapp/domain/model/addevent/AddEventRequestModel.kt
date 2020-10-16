package br.com.zeventis.managerapp.domain.model.addevent

import android.os.Parcelable
import java.math.BigDecimal
import java.time.LocalDateTime
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AddEventRequestModel(
    val name: String,
    val date: String,
    val base64Image: String? = null,
    val defaultActionsPerPromoter: Int,
    val ticketPrice: BigDecimal,
    val minimumActionsRequiredPerPromoter: Int
) : Parcelable