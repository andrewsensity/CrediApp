package com.sistecredito.creditapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Users(
    val cc: Int,
    val credit: Int,
    val fee: Int
): Parcelable
