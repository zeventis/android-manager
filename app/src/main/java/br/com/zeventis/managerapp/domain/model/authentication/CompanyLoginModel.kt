package br.com.zeventis.managerapp.domain.model.authentication

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CompanyLoginModel(
        val name: String,
        val cep: String,
        val phone: String,
        val addressComplement: String,
        val addressNumber: String
) : Parcelable