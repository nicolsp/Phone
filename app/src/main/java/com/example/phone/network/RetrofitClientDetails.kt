package com.example.phone.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClientDetails {
    companion object {
        private const val URL_BASE2 = "http://my-json-server.typicode.com/Himuravidal/FakeAPIdata/"
        fun getRetrofitClienteDetails(): DetailsPhoneApi {
            val mRetrofitDetails = Retrofit.Builder()
                .baseUrl(URL_BASE2)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return mRetrofitDetails.create(DetailsPhoneApi::class.java)

        }
    }
}