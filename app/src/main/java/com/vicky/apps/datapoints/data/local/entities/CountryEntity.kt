package com.vicky.apps.datapoints.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class CountryEntity {

    @PrimaryKey(autoGenerate = true)
    var countryId: Int =0

    @ColumnInfo(name ="name")
    val name: String = ""

    @ColumnInfo(name ="flag")
    val flag: String = ""

    @ColumnInfo(name ="nativeName")
    val nativeName: String = ""

    @ColumnInfo(name ="capital")
    val capital: String = ""

    @ColumnInfo(name ="population")
    val population: Int = 0

    @ColumnInfo(name ="subregion")
    val subRegion: String = ""

    @ColumnInfo(name ="cioc")
    val cioc: String = ""


}