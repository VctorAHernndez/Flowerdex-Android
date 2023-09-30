package com.example.floradex20.services

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TOKEN_ANDRES: String = "cMyugCJvk-U_xoi31FVBgoEqZiuRnDYpP8fv8Vfiejg"
private const val BASE_URL = "https://ada.uprrp.edu/" // "https://trefle.io/"

object ServiceBuilder {
    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}