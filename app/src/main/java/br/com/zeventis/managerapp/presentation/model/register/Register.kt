package br.com.zeventis.managerapp.presentation.model.register

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Register(
    var name: String = "",
    var city: String = "",
    var email: String = "",
    var birthdayDate: String = "",
    var password: String = "",
    var phone: String = "",
    var profile: String = "1",
    var state: String= "",
    var username: String = "",
    var gender: String = "",
    var company: Company = Company(),
) : Parcelable
