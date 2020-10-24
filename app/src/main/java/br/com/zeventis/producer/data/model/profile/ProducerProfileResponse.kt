package br.com.zeventis.producer.data.model.profile

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProducerProfileResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("birthdayDate") val birthdayDate: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("state") val state: String,
    @SerializedName("city") val city: String,
    @SerializedName("profile") val profile: String,
    @SerializedName("token") val token: String,
    @SerializedName("instagram") val instagram: String?,
    @SerializedName("company") val company: CompanyProfileResponse,
) : Parcelable

@Parcelize
data class CompanyProfileResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("addressNumber") val addressNumber: String,
    @SerializedName("addressComplement") val addressComplement: String,
    @SerializedName("cep") val cep: String,
    @SerializedName("name") val name: String,
    @SerializedName("phone") val phone: String,
) : Parcelable
