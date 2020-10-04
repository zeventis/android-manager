package br.com.zeventis.managerapp.presentation.model.register

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Register(
    val name: String,
    val city: String,
    val email: String,
    val birthdayDate: String,
    val password: String,
    val phone: String,
    val profile: Int,
    val state: String,
    val username: String,
    val company: Company,
) : Parcelable
