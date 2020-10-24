package com.example.phone.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.phone.PhoneRepository
import com.example.phone.local.PhoneDataBase
import com.example.phone.local.RealPhone

class PhoneViewModel(application: Application): AndroidViewModel(application) {
    private val mRepository : PhoneRepository
    val liveDataFromLocal : LiveData<List<RealPhone>>

    init {
        val phoDao = PhoneDataBase.getDataBase(application).phoneDao()
        mRepository = PhoneRepository(phoDao)
        mRepository.getDataFromServe()

        liveDataFromLocal = mRepository.mLiveData
    }

    fun exposeLiveDataFromDataBase(): LiveData<List<RealPhone>> {
        return mRepository.mLiveData
    }
    fun obtainPhoneByID(id: Int): LiveData<RealPhone> {
        return mRepository.obtainPhoneByID(id)
    }

}