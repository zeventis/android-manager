package br.com.zeventis.managerapp.data.model.register

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegisterRequest(
    @SerializedName("name") val name: String,
    @SerializedName("city") val city: String,
    @SerializedName("email") val email: String,
    @SerializedName("birthdayDate") val birthdayDate: String,
    @SerializedName("password") val password: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("profile") val profile: Int,
    @SerializedName("state") val state: String,
    @SerializedName("username") val username: String,
    @SerializedName("company") val company: CompanyRegisterRequest,
) : Parcelable
