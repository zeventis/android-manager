package br.com.zeventis.managerapp.domain.model.register

import android.os.Parcelable
import br.com.zeventis.managerapp.domain.enum.ProfileTypeEnum
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegisterModel(
    val name: String,
    val city: String,
    val email: String,
    val birthdayDate: String,
    val phone: String,
    val profile: ProfileTypeEnum = ProfileTypeEnum.FREE,
    val state: String,
    val gender: String,
    val instagram: String? = null,
    val company: CompanyRegisterModel? = null,
    val companyId: Long? = null,
    val username: String,
    val password: String,
) : Parcelable

@Parcelize
data class CompanyRegisterModel(
    val name: String,
    val phone: String,
    val cep: String,
    val addressComplement: String,
    val addressNumber: String,
) : Parcelable
