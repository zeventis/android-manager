package br.com.zeventis.managerapp.presentation.model.register

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Company(
    var name: String = "",
    var phone: String = "",
    var cep: String = "",
    var addressComplement: String = "",
    var addressNumber: String = "",
) : Parcelable
