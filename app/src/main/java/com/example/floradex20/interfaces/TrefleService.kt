package com.example.floradex20.interfaces

import com.example.floradex20.models.FlowersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TrefleService {
    @GET("/api/v1/plants/")
    //Quite el id de la planta para recibir mas cosas
    //Posiblemente se puede overload la funcion para q funcione sin id y con id
    fun getPlants(@Query("token") token: String): Call<FlowersResponse>

    //El Api Query que se usa cuando se hace un search
    @GET("/api/v1/plants/search/")
    fun getPlantsbyName(@Query("token") token: String,
                        @Query("q") q: String): Call<FlowersResponse>

    //Api query para el filter
    @GET("api/v1/plants/")
    fun getFiltered(@Query("token") token: String,
                    @Query("filter[edible]") edible: String,
                    @Query("filter[vegetable]") vegetable: String,
                    @Query("filter[flower_color]") color: String,
                    @Query("filter[scientific_name]") cien_name: String): Call<FlowersResponse>
}

//@Query("filter[id]") id: Int