package br.com.zeventis.producer.domain.model.register

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CompanyRegisterSearchModel(
    val addressNumber: String,
    val addressComplement: String,
    val cep: String,
    val name: String,
    val phone: String,
) : Parcelable