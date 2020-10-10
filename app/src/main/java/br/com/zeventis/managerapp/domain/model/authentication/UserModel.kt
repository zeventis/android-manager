package br.com.zeventis.managerapp.domain.model.authentication

import android.os.Parcelable
import br.com.zeventis.managerapp.domain.enum.ProfileTypeEnum
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
    val profile: ProfileTypeEnum,
    val gender: String,
    val instagram: String,
    val company: CompanyLoginModel,
) : Parcelable

@Parcelize
data class CompanyLoginModel(
    val name: String,
    val cep: String,
    val phone: String,
    val addressComplement: String,
    val addressNumber: String
) : Parcelable
