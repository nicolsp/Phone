package com.example.phone

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.phone.local.PhoneDao
import com.example.phone.local.RealPhone
import com.example.phone.network.RetrofitClient
import com.example.phone.pojos.PhontEntityItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhoneRepository (private val phoneDao: PhoneDao){
    private val Retroservice = RetrofitClient.getRetrofitCliente()
    val mLiveData = phoneDao.showAllPhone()

    fun obtainPhoneByID(id: Int): LiveData<RealPhone> {
        return phoneDao.showOnPhoneByID(id)
    }

    fun getDataFromServe() {
        val call = Retroservice.fetAllPhone()
        call.enqueue(object : Callback<List<PhontEntityItem>> {
            override fun onResponse(
                call: Call<List<PhontEntityItem>>,
                response: Response<List<PhontEntityItem>>
            ) {
             when(response.code()) {
                 in 200..299 -> CoroutineScope(Dispatchers.IO).launch {
                     Log.d("RESPONDEOOOK",response.code().toString())
                     response.body()?.let {
                         phoneDao.insertAllPhone(convert(it))
                     }
                 }
                 in 300..509 -> Log.d("RESPONSE_300", response.body().toString())
                 else -> Log.d("ERROR",response.errorBody().toString())
             }
            }

            override fun onFailure(call: Call<List<PhontEntityItem>>, t: Throwable) {
                Log.e("ERROR",t.message.toString())
            }

        })
    }
    fun convert(listFromNetwork: List<PhontEntityItem>): List<RealPhone> {
        val listmutable = mutableListOf<RealPhone>()
        listFromNetwork.map {
            listmutable.add(
                RealPhone(it.id,
            it.image,
            it.name,
            it.price)
            )
        }
        return listmutable
    }
}