package br.com.zeventis.managerapp.presentation.model.authentication

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Login(
    val username: String,
    val password: String
) : Parcelable