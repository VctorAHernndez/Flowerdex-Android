package com.example.floradex20.models

import com.google.gson.annotations.SerializedName

data class User (
   @SerializedName("username") val username: String?,
   @SerializedName("email") val email: String?,
   @SerializedName("password") val password: String?,
   @SerializedName("id") val id: String?,
   @SerializedName("error") val error: String?
)
