package br.com.zeventis.producer.data.model.requesteventpromoter

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RequestPromoterEventRequest(
    @SerializedName("status") val status: String
) : Parcelable