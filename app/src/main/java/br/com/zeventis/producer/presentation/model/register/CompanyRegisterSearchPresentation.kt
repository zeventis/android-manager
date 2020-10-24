package br.com.zeventis.producer.presentation.model.register

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CompanyRegisterSearchPresentation(
    val addressNumber: String,
    val addressComplement: String,
    val cep: String,
    val name: String,
    val phone: String,
) : Parcelable