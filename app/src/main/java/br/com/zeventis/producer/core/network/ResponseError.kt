package br.com.zeventis.producer.core.network

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseError(
    @SerializedName("errorMessages") val errorMessages: List<Error>
) : Parcelable

@Parcelize
data class Error(
    @SerializedName("code") val code: String,
    @SerializedName("message") val message: String,
    @SerializedName("info") val info: String,
    @SerializedName("description") val description: String,
) : Parcelable

