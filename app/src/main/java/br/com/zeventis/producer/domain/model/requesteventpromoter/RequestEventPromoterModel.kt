package br.com.zeventis.producer.domain.model.requesteventpromoter

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RequestEventPromoterModel(
    val status: String
) : Parcelable