package br.com.zeventis.managerapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginModel(
    val login: String,
    val password: String
) : Parcelable