package br.com.zeventis.producer.presentation.model.profile

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProducerProfileResponseModel(
    val id: Long,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val birthdayDate: String,
    val gender: String,
    val state: String,
    val city: String,
    val profile: String,
    val token: String,
    val instagram: String,
    val company: CompanyProfileResponseModel,
) : Parcelable

@Parcelize
data class CompanyProfileResponseModel(
    val id: Long,
    val addressNumber: String,
    val addressComplement: String,
    val cep: String,
    val name: String,
    val phone: String,
) : Parcelable
