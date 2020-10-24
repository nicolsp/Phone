package com.example.phone

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.phone.detailspojo.DetailsEntity
import com.example.phone.local.DetailsPhoneDao
import com.example.phone.local.RealDetailsPhone
import com.example.phone.local.RealPhone
import com.example.phone.network.RetrofitClientDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsRepository(private val detailsDao: DetailsPhoneDao) {
    private val Retrodetails = RetrofitClientDetails.getRetrofitClienteDetails()
    val mLiveDetails = detailsDao.showAllDetails()

    fun obtainDetailsByID(id: Int): LiveData<RealDetailsPhone> {
        return detailsDao.showOnDetailsByID(id)
    }

    fun getDataFromServerDetails() {
        val call2 = Retrodetails.fetAllDetails()
        call2.enqueue(object : Callback<List<DetailsEntity>> {
            override fun onResponse(
                call: Call<List<DetailsEntity>>,
                response: Response<List<DetailsEntity>>
            ) {
                when (response.code()) {
                    in 200..299 -> CoroutineScope(Dispatchers.IO).launch {
                        Log.d("RESPONDEDETAILSOK", response.code().toString())
                        response.body()?.let {
                            detailsDao.insertAllDetails(convert2(it))
                        }
                    }
                    in 300..509 -> Log.d("RESPONDE_300", response.body().toString())
                    else -> Log.d("ERROR", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<List<DetailsEntity>>, t: Throwable) {
                Log.e("ERROR", t.message.toString())
            }

        })
    }

       private fun convert2(listFromDetails: List<DetailsEntity>): List<RealDetailsPhone> {
            val listmutabledetails = mutableListOf<RealDetailsPhone>()
            listFromDetails.map {
                listmutabledetails.add(
                    RealDetailsPhone(
                        it.description,
                        it.id,
                        it.image,
                        it.lastPrice,
                        it.name,
                        it.price
                    )
                )
            }
            return listmutabledetails

        }
    }



