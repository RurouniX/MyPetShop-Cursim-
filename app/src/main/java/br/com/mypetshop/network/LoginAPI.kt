package br.com.mypetshop.network

import br.com.mypetshop.network.model.LoginCredentials
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginAPI {

    @POST("/login")
    fun doLogin(@Body login: LoginCredentials): Call<Void>

}