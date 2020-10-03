package br.com.zeventis.managerapp.data.model.authentication

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProducerResponse(
        @SerializedName("cep") val cep: String,
        @SerializedName("phone") val phone: String,
        @SerializedName("addressComplement") val addressComplement: String,
        @SerializedName("addressNumber") val addressNumber: String
) : Parcelable