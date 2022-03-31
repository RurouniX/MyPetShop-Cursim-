package br.com.mypetshop.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConfig {
    private lateinit var retrofit: Retrofit

    fun getInstance(): Retrofit {
        if (!this::retrofit.isInitialized)
            retrofit = buildRetrofit()
        return retrofit
    }

    private fun buildRetrofit(): Retrofit {
        retrofit = Retrofit.Builder()
            .baseUrl("https://mypetshop.mocklab.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }

    fun getLoginApi(): LoginAPI {
        return getInstance().create(LoginAPI::class.java)
    }
}