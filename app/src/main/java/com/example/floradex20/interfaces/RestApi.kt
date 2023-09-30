package com.example.floradex20.interfaces

import com.example.floradex20.models.FlowersResponse
import com.example.floradex20.models.User
import retrofit2.Call
import retrofit2.http.*

interface RestApi {

    @Headers("Content-Type: application/json")
    @POST("~victor.hernandez17/flowerdex/signup.php")
    fun addUser(@Body userData: User): Call<User>

    @Headers("Content-Type: application/json")
    @POST("~victor.hernandez17/flowerdex/login.php")
    fun logInUser(@Body userData: User): Call<User>


    @GET("~victor.hernandez17/flowerdex/listFlowers.php") // /api/v1/plants/
    //Quite el id de la planta para recibir mas cosas
    //Posiblemente se puede overload la funcion para q funcione sin id y con id
    fun getPlants(@Query("user_id") user_id: String): Call<FlowersResponse>

    //El Api Query que se usa cuando se hace un search
    @GET("~victor.hernandez17/flowerdex/listFlowers.php") // /api/v1/plants/search/
    fun getPlantsbyName(@Query("user_id") user_id: String,
                        @Query("q") q: String): Call<FlowersResponse>

    //Api query para el filter
    @GET("~victor.hernandez17/flowerdex/listFlowers.php") // api/v1/plants/
    fun getFiltered(@Query("user_id") user_id: String,
                    @Query("edible") edible: String,
                    @Query("vegetable") vegetable: String,
                    @Query("flower_color") color: String, // TODO: get rid of this, as it it's not used by ADA
                    @Query("scientific_name") scientificName: String): Call<FlowersResponse>
    
}