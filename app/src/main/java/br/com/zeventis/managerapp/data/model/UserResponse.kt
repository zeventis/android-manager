package br.com.zeventis.managerapp.data.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("token") val token: String,
    @SerializedName("name") val name: String
)
