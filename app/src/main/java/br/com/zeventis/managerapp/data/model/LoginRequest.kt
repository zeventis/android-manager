package br.com.zeventis.managerapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginRequest(
    @SerializedName("login") val login: String,
    @SerializedName("password") val password: String
) : Parcelable