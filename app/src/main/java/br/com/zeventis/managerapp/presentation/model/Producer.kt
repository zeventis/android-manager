package br.com.zeventis.managerapp.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Producer(
    val cep: String,
    val phone: String,
    val addressComplement: String,
    val addressNumber: String
) : Parcelable