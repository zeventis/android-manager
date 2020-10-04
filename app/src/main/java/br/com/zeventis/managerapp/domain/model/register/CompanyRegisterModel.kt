package br.com.zeventis.managerapp.domain.model.register

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CompanyRegisterModel(
    val name: String,
    val phone: String,
    val cep: String,
    val addressComplement: String,
    val addressNumber: String,
) : Parcelable
