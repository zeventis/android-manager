package br.com.zeventis.managerapp.presentation.model.register

import android.os.Parcelable
import br.com.zeventis.managerapp.domain.enum.ProfileTypeEnum
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Register(
    var name: String = "",
    var city: String = "",
    var email: String = "",
    var birthdayDate: String = "",
    var phone: String = "",
    var profile: ProfileTypeEnum = ProfileTypeEnum.FREE,
    var state: String = "",
    var gender: String = "",
    var instagram: String = "",
    var company: Company? = Company(),
    var companyId: Long = Long.MIN_VALUE,
    var username: String = "",
    var password: String = "",
) : Parcelable

@Parcelize
data class Company(
    var name: String = "",
    var phone: String = "",
    var cep: String = "",
    var addressComplement: String = "",
    var addressNumber: String = "",
) : Parcelable
