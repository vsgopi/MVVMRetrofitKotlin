package com.teemy.mvvmretrofitkotlin.network

import com.teemy.mvvmretrofitkotlin.model.User
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("place your end point here")
    fun getUsers(): Call<ArrayList<User>>
}