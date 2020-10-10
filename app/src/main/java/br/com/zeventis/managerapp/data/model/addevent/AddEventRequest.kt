package br.com.zeventis.managerapp.data.model.addevent

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AddEventRequest(
    @SerializedName("name") val name: String,
    @SerializedName("date") val date: String,
    @SerializedName("imageUrl") val imageUrl: String? = null,
    @SerializedName("promotersNumber") val promotersNumber: Int,
    @SerializedName("defaultActionsPerPromoter") val defaultActionsPerPromoter: Int,
    @SerializedName("minimumActionsRequiredPerPromoter") val minimumActionsRequiredPerPromoter: Int
) : Parcelable