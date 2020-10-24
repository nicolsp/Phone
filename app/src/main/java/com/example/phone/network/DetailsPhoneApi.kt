package com.example.phone.network

import com.example.phone.detailspojo.DetailsEntity
import retrofit2.Call
import retrofit2.http.GET

interface DetailsPhoneApi {
    @GET("details/{id}")
    fun fetAllDetails(): Call<List<DetailsEntity>>
}