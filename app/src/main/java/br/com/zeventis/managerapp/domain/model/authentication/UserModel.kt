package br.com.zeventis.managerapp.domain.model.authentication

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel(
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val birthdayDate: String,
    val state: String,
    val city: String,
    val token: String,
    val producer: ProducerModel,
) : Parcelable
