package br.com.zeventis.managerapp.data.model.authentication

import android.os.Parcelable
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
    @SerializedName("token") val token: String,
    @SerializedName("producer") val producer: ProducerResponse,
) : Parcelable
