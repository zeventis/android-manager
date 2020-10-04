package br.com.zeventis.managerapp.data.model.register

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CompanyRegisterRequest(
    @SerializedName("name") val name: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("cep") val cep: String,
    @SerializedName("addressComplement") val addressComplement: String,
    @SerializedName("addressNumber") val addressNumber: String,
) : Parcelable
