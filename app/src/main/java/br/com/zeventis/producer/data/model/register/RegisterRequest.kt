package br.com.zeventis.producer.data.model.register

import android.os.Parcelable
import br.com.zeventis.producer.domain.enum.ProfileTypeEnum
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegisterRequest(
    @SerializedName("name") val name: String,
    @SerializedName("city") val city: String,
    @SerializedName("email") val email: String,
    @SerializedName("birthdayDate") val birthdayDate: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("profile") val profile: ProfileTypeEnum,
    @SerializedName("state") val state: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("instagram") val instagram: String? = null,
    @SerializedName("company") val company: CompanyRegisterRequest? = null,
    @SerializedName("companyId") val companyId: Long? = null,
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String
) : Parcelable

@Parcelize
data class CompanyRegisterRequest(
    @SerializedName("name") val name: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("cep") val cep: String,
    @SerializedName("addressComplement") val addressComplement: String,
    @SerializedName("addressNumber") val addressNumber: String,
) : Parcelable
