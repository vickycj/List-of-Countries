package com.vicky.apps.datapoints.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class CountryEntity {

    @PrimaryKey(autoGenerate = true)
    var countryId: Int =0

    @ColumnInfo(name ="name")
    var name: String = ""

    @ColumnInfo(name ="flag")
    var flag: String = ""

    @ColumnInfo(name ="nativeName")
    var nativeName: String = ""

    @ColumnInfo(name ="capital")
    var capital: String = ""

    @ColumnInfo(name ="population")
    var population: Int = 0

    @ColumnInfo(name ="subregion")
    var subRegion: String = ""

    @ColumnInfo(name ="cioc")
    var cioc: String = ""


}