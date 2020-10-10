package br.com.zeventis.managerapp.presentation.model.requesteventpromoter

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RequestEventPromoter(
    val status: String
) : Parcelable