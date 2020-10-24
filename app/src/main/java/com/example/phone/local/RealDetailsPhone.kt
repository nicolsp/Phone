package com.example.phone.local

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( tableName = "details_table")
data class RealDetailsPhone( @PrimaryKey @NonNull   val description: String ,
                                                    val id: Int,
                                                    val image: String,
                                                    val lastPrice: Int,
                                                    val name: String,
                                                    val price: Int

                             )