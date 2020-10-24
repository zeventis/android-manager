package br.com.zeventis.producer.data.model.authentication

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginRequest(
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String
) : Parcelable