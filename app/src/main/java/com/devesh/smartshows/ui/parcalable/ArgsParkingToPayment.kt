package com.devesh.smartshows.ui.parcalable

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ArgsMovieToPayment(val pillor: String, val movieBox: String, val building: String,val floor:String) :
    Parcelable