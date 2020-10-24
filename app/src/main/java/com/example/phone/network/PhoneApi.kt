package com.example.phone.network

import com.example.phone.pojos.PhontEntityItem
import retrofit2.Call
import retrofit2.http.GET

interface PhoneApi {

    @GET("products")
    fun fetAllPhone(): Call<List<PhontEntityItem>>
}