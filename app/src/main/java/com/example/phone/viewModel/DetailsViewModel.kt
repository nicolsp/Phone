package com.example.phone.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.phone.DetailsRepository
import com.example.phone.local.DetailsDataBase
import com.example.phone.local.RealDetailsPhone

class DetailsViewModel (application: Application): AndroidViewModel(application){
    private val mRepositoryD : DetailsRepository
    val liveDataFromLocalDetails : LiveData<List<RealDetailsPhone>>


    init {
        val detaDao = DetailsDataBase.getDataBase(application).detailsDao()
        mRepositoryD = DetailsRepository(detaDao)
        mRepositoryD.getDataFromServerDetails()

        liveDataFromLocalDetails =mRepositoryD.mLiveDetails
    }
    fun exposeLiveDataFromDataBaseDetails(): LiveData<List<RealDetailsPhone>> {
        return mRepositoryD.mLiveDetails
    }

    fun obtainPhoneByID(id: Int): LiveData<RealDetailsPhone> {
        return mRepositoryD.obtainDetailsByID(id)
    }
}