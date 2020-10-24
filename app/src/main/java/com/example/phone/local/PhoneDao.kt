package com.example.phone.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PhoneDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllPhone(mList: List<RealPhone>)

    @Query("SELECT * FROM phone_table")
    fun showAllPhone(): LiveData<List<RealPhone>>

    @Query("SELECT*FROM")

}