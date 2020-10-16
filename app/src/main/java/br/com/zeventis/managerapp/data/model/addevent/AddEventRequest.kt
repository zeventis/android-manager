package br.com.zeventis.managerapp.data.model.addevent

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.math.BigDecimal
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AddEventRequest(
    @SerializedName("name") val name: String,
    @SerializedName("date") val date: String,
    @SerializedName("base64Image") val base64Image: String? = null,
    @SerializedName("defaultActionsPerPromoter") val defaultActionsPerPromoter: Int,
    @SerializedName("ticketPrice") val ticketPrice: BigDecimal,
    @SerializedName("minimumActionsRequiredPerPromoter") val minimumActionsRequiredPerPromoter: Int
) : Parcelable