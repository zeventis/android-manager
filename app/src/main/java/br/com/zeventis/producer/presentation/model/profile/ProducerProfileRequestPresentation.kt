package br.com.zeventis.producer.presentation.model.profile

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProducerProfileRequestModel(
    val name: String? = null,
    val username: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val birthdayDate: String? = null,
    val gender: String? = null,
    val state: String? = null,
    val city: String? = null,
    val profile: String? = null,
    val token: String? = null,
    val instagram: String? = null,
    val company: CompanyProfileRequestModel? = null,
) : Parcelable

@Parcelize
data class CompanyProfileRequestModel(
    val addressNumber: String? = null,
    val addressComplement: String? = null,
    val cep: String? = null,
    val name: String? = null,
    val phone: String? = null,
) : Parcelable
