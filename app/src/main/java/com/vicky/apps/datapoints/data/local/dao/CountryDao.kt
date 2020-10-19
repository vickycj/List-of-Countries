package com.vicky.apps.datapoints.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vicky.apps.datapoints.data.local.entities.CountryEntity
import com.vicky.apps.datapoints.ui.model.CountryBasicInfo
import io.reactivex.Flowable
import io.reactivex.Single


@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCountryList(book: CountryEntity)

    @Query("DELETE FROM CountryEntity")
    fun emptyCountryList()

    @Query(value = "SELECT name, flag FROM CountryEntity")
    fun getAllCountries() : Flowable<List<CountryBasicInfo>>

    @Query(value = "SELECT * FROM CountryEntity WHERE countryId = :id")
    fun getCountryById(id: Int) : Single<CountryEntity>

    @Query("SELECT COUNT(countryId) FROM CountryEntity")
    fun getCountryRowCount(): Single<Int?>?
}