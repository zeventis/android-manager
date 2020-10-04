package br.com.zeventis.managerapp.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val birthdayDate: String,
    val state: String,
    val city: String,
    val token: String,
    val company: Company,
) : Parcelable
