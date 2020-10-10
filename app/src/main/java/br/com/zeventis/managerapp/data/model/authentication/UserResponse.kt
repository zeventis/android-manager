package br.com.zeventis.managerapp.data.model.authentication

import android.os.Parcelable
import br.com.zeventis.managerapp.domain.enum.ProfileTypeEnum
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserResponse(
    @SerializedName("name") val name: String,
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("birthdayDate") val birthdayDate: String,
    @SerializedName("state") val state: String,
    @SerializedName("city") val city: String,
    @SerializedName("profile") val profile: ProfileTypeEnum,
    @SerializedName("token") val token: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("instagram") val instagram: String,
    @SerializedName("company") val company: CompanyLoginResponse,
) : Parcelable

@Parcelize
data class CompanyLoginResponse(
    @SerializedName("name") val name: String,
    @SerializedName("cep") val cep: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("addressComplement") val addressComplement: String,
    @SerializedName("addressNumber") val addressNumber: String
) : Parcelable