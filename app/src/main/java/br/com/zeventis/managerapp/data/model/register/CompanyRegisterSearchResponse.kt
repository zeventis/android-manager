package br.com.zeventis.managerapp.data.model.register

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CompanyRegisterSearchResponse(
    @SerializedName("addressNumber") val addressNumber: String,
    @SerializedName("addressComplement") val addressComplement: String,
    @SerializedName("cep") val cep: String,
    @SerializedName("name") val name: String,
    @SerializedName("phone") val phone: String,
) : Parcelable