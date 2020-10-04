package br.com.zeventis.managerapp.presentation.model.login

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Login(
    val username: String,
    val password: String
) : Parcelable