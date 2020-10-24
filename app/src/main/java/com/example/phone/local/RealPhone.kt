package com.example.phone.local

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "phone_table")
data class RealPhone(@PrimaryKey @NonNull val id: Int,
                                val image: String,
                     val name: String,
                     val price: Int
                     )