package br.com.zeventis.producer.data.model.updateevent

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventUpdateResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("date") val date: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("promotersNumber") val promotersNumber: Int,
    @SerializedName("defaultActionsPerPromoter") val defaultActionsPerPromoter: Int,
    @SerializedName("minimumActionsRequiredPerPromoter") val minimumActionsRequiredPerPromoter: Int,
    @SerializedName("eventCode") val eventCode: String
) : Parcelable