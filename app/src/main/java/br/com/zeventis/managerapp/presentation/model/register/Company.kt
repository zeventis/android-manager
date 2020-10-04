package br.com.zeventis.managerapp.presentation.model.register

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Company(
    val name: String,
    val phone: String,
    val cep: String,
    val addressComplement: String,
    val addressNumber: String,
) : Parcelable
