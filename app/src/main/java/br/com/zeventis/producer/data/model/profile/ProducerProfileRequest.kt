package br.com.zeventis.producer.data.model.profile

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProducerProfileRequest(
    @SerializedName("name") val name: String? = null,
    @SerializedName("username") val username: String? = null,
    @SerializedName("email") val email: String? = null,
    @SerializedName("phone") val phone: String? = null,
    @SerializedName("birthdayDate") val birthdayDate: String? = null,
    @SerializedName("gender") val gender: String? = null,
    @SerializedName("state") val state: String? = null,
    @SerializedName("city") val city: String? = null,
    @SerializedName("profile") val profile: String? = null,
    @SerializedName("token") val token: String? = null,
    @SerializedName("instagram") val instagram: String? = null,
    @SerializedName("company") val company: CompanyProfileRequest? = null,
) : Parcelable

@Parcelize
data class CompanyProfileRequest(
    @SerializedName("addressNumber") val addressNumber: String? = null,
    @SerializedName("addressComplement") val addressComplement: String? = null,
    @SerializedName("cep") val cep: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("phone") val phone: String? = null,
) : Parcelable
