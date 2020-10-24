package br.com.zeventis.producer.presentation.model.authentication

import android.os.Parcelable
import br.com.zeventis.producer.domain.enum.ProfileTypeEnum
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
    val profile: ProfileTypeEnum,
    val token: String,
    val gender: String,
    val instagram: String,
    val company: Company,
) : Parcelable

@Parcelize
data class Company(
    val name: String,
    val cep: String,
    val phone: String,
    val addressComplement: String,
    val addressNumber: String
) : Parcelable
