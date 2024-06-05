package com.example.apiapplication

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("products")//here product is nothing but end point of url
    fun getProducts():Call<MyData> //call is retrofit function
}