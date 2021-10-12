package com.example.headupgameretrofit

import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {

    @GET("/celebrities/")
    fun getUsers() : Call<List<HeadsUpDetaillsItem>>
}