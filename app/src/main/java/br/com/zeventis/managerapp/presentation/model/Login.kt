package br.com.zeventis.managerapp.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Login(
    val username: String,
    val password: String
) : Parcelable