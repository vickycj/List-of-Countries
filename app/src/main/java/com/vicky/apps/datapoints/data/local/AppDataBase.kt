package com.vicky.apps.datapoints.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vicky.apps.datapoints.data.local.dao.CountryDao
import com.vicky.apps.datapoints.data.local.entities.CountryEntity

@Database(entities = [(CountryEntity::class)],version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun countryDAO() : CountryDao
}