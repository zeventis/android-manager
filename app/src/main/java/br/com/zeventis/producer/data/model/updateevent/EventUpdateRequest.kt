package br.com.zeventis.producer.data.model.updateevent

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventUpdateRequest(
    @SerializedName("name") val name: String? = null,
    @SerializedName("date") val date: String? = null,
    @SerializedName("imageUrl") val imageUrl: String? = null,
    @SerializedName("promotersNumber") val promotersNumber: Int? = null,
    @SerializedName("defaultActionsPerPromoter") val defaultActionsPerPromoter: Int? = null,
    @SerializedName("minimumActionsRequiredPerPromoter") val minimumActionsRequiredPerPromoter: Int? = null
) : Parcelable