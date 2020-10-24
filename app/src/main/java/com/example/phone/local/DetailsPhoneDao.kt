package com.example.phone.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DetailsPhoneDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllDetails(mList: List<RealDetailsPhone>)

    @Query("SELECT*FROM details_table")
    fun showAllDetails(): LiveData<List<RealDetailsPhone>>

    @Query("SELECT*FROM details_table WHERE id =:mId2")
    fun showOnDetailsByID(mId2: Int): LiveData<RealDetailsPhone>

    @Update
    suspend fun updateDetails(detailsPhone: RealDetailsPhone)




}