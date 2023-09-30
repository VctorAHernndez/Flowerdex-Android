package com.example.floradex20

import android.view.View
import com.example.floradex20.interfaces.RestApi
import com.example.floradex20.models.FlowersResponse
import com.example.floradex20.models.User
import com.example.floradex20.services.ServiceBuilder
import com.google.android.material.snackbar.Snackbar
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiManager(val parent: View) {


    fun addUser(userData: User, onResult: (User?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.addUser(userData).enqueue(object: Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Snackbar.make(parent, "${t.message}", Snackbar.LENGTH_SHORT).show()
            }
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.code() == 500 || response.code() == 400) {
                    val responseString = response.errorBody()!!.string()
                    val message = JSONObject(responseString).getString("error")
                    Snackbar.make(parent, message, Snackbar.LENGTH_SHORT).show()
                } else {
                    onResult(response.body())
                }
            }
        })
    }


    fun logInUser(userData: User, onResult: (User?) -> Unit) {
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.logInUser(userData).enqueue(object: Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Snackbar.make(parent, "${t.message}", Snackbar.LENGTH_SHORT).show()
            }
            override fun onResponse(call: Call<User>, response: Response<User>) {

                if(response.code() == 500 || response.code() == 400) {
                    val responseString = response.errorBody()!!.string()
                    val message = JSONObject(responseString).getString("error")
                    Snackbar.make(parent, message, Snackbar.LENGTH_SHORT).show()
                } else {
                    onResult(response.body())
                }
            }
        })
    }


    fun getPlants(user_id: String, onResult: (FlowersResponse) -> Unit) {
        val request = ServiceBuilder.buildService(RestApi::class.java)
        request.getPlants(user_id).enqueue(object: Callback<FlowersResponse> {
            override fun onFailure(call: Call<FlowersResponse>, t: Throwable) {
                Snackbar.make(parent, "${t.message}", Snackbar.LENGTH_SHORT).show()
            }
            override fun onResponse(call: Call<FlowersResponse>, response: Response<FlowersResponse>) {
                if (response.code() == 500 || response.code() == 400) {
                    val responseString = response.errorBody()!!.string()
                    val message = JSONObject(responseString).getString("error")
                    Snackbar.make(parent, message, Snackbar.LENGTH_SHORT).show()
                } else {
                    onResult(response.body()!!)
                }
            }
        })
    }


    fun getPlantsByName(user_id: String, search_input: String, onResult: (FlowersResponse) -> Unit) {
        val request = ServiceBuilder.buildService(RestApi::class.java)
        request.getPlantsbyName(user_id, search_input).enqueue(object: Callback<FlowersResponse> {
            override fun onFailure(call: Call<FlowersResponse>, t: Throwable) {
                Snackbar.make(parent, "${t.message}", Snackbar.LENGTH_SHORT).show()
            }
            override fun onResponse(call: Call<FlowersResponse>, response: Response<FlowersResponse>) {
                if (response.code() == 500 || response.code() == 400) {
                    val responseString = response.errorBody()!!.string()
                    val message = JSONObject(responseString).getString("error")
                    Snackbar.make(parent, message, Snackbar.LENGTH_SHORT).show()
                } else {
                    onResult(response.body()!!)
                }
            }
        })
    }


    fun getFiltered(user_id: String, edible: String, vegetable: String, color: String, scientificName: String, onResult: (FlowersResponse) -> Unit) {
        val request = ServiceBuilder.buildService(RestApi::class.java)
        request.getFiltered(user_id, edible, vegetable, color, scientificName).enqueue(object: Callback<FlowersResponse> {
            override fun onFailure(call: Call<FlowersResponse>, t: Throwable) {
                Snackbar.make(parent, "${t.message}", Snackbar.LENGTH_SHORT).show()
            }
            override fun onResponse(call: Call<FlowersResponse>, response: Response<FlowersResponse>) {
                if (response.code() == 500 || response.code() == 400) {
                    val responseString = response.errorBody()!!.string()
                    val message = JSONObject(responseString).getString("error")
                    Snackbar.make(parent, message, Snackbar.LENGTH_SHORT).show()
                } else {
                    onResult(response.body()!!)
                }
            }
        })
    }


}