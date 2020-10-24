package com.example.phone.local

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase

import androidx.room.Room

@Database(entities = [RealDetailsPhone::class],version = 1)

abstract class DetailsDataBase: RoomDatabase() {

    abstract fun detailsDao(): DetailsPhoneDao
    companion object {
        @Volatile
        private var INSTANCE : DetailsDataBase? = null

        fun getDataBase(context: Context): DetailsDataBase {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context,
                DetailsDataBase::class.java,"detailsDb")
                    .build()
                INSTANCE = instance
                return instance

            }

        }
    }

}