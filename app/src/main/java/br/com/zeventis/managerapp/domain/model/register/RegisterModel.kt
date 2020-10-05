package br.com.zeventis.managerapp.domain.model.register

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegisterModel(
    val name: String,
    val city: String,
    val email: String,
    val birthdayDate: String,
    val password: String,
    val phone: String,
    val profile: String,
    val state: String,
    val username: String,
    val gender: String,
    val company: CompanyRegisterModel,
) : Parcelable
