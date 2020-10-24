package com.example.phone.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PhoneDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllPhone(mList: List<RealPhone>)

    @Query("SELECT * FROM phone_table")
    fun showAllPhone(): LiveData<List<RealPhone>>

    @Query("SELECT*FROM phone_table WHERE id =:mId")
    fun showOnPhoneByID(mId: Int): LiveData<RealPhone>

    @Update
    suspend fun updatePhone(realphone: RealPhone)

}