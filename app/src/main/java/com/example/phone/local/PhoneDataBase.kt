package com.example.phone.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RealPhone::class], version = 1)
abstract class PhoneDataBase(): RoomDatabase() {
    abstract fun phoneDao(): PhoneDao

    companion object {
        @Volatile
        private var INSTANCE : PhoneDataBase? = null
        fun getDataBase(context: Context): PhoneDataBase {
            val tempInstance = INSTANCE
            if(tempInstance != null ) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context,
                PhoneDataBase::class.java,"phoneDb")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}

