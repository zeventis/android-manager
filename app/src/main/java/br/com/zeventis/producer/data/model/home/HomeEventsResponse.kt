package br.com.zeventis.producer.data.model.home

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.math.BigDecimal
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HomeEventsResponse(
    @SerializedName("date") val date: String,
    @SerializedName("events") val events: List<HomeEventResponse>
) : Parcelable

@Parcelize
data class HomeEventResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("date") val date: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("sumTicketPriceCost") val sumTicketPrice: BigDecimal,
    @SerializedName("percentProgress") val percentProgress: Int,
    @SerializedName("promotersNumber") val promotersNumber: Int
) : Parcelable