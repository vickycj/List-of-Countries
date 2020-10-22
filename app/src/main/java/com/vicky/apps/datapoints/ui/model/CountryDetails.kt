package com.vicky.apps.datapoints.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class KeyValue(var tile: String, var value: String) : Parcelable
