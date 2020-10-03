package br.com.zeventis.managerapp.domain.model.authentication

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProducerModel(
        val cep: String,
        val phone: String,
        val addressComplement: String,
        val addressNumber: String
) : Parcelable