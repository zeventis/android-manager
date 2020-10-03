package br.com.zeventis.managerapp.domain.model.authentication

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginModel(
    val user: String,
    val password: String
) : Parcelable