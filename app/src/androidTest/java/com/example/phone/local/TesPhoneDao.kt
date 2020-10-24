package com.example.phone.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.runBlocking
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TesPhoneDao {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var mPhoneDao: PhoneDao
    private lateinit var db : PhoneDataBase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context,PhoneDataBase::class.java).build()
        mPhoneDao = db.phoneDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertListElemento_happy_case() = runBlocking {
        val phoneLkist = listOf(RealPhone(1,
                       "wsas",
                       "akjs", 234
            ))

        mPhoneDao.insertAllPhone(phoneLkist)

        mPhoneDao.showAllPhone().observeForever {
            assertThat(it).isNotEmpty()
            assertThat(it[0].id).isEqualTo(1)
            assertThat(it).hasSize(1)
        }
    }


    @Test
    fun obtainPhoneByID() = runBlocking {
        val id = 1
        val phoneList = listOf(RealPhone(1,
        "jajja",
        "kajakja",
        123))

        mPhoneDao.insertAllPhone(phoneList)
        mPhoneDao.showOnPhoneByID(id).observeForever {
            assertThat(it.id).isEqualTo(id)
        }
    }
}