package br.com.mypetshop.network.model

import com.google.gson.annotations.SerializedName

class LoginCredentials(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String
)
