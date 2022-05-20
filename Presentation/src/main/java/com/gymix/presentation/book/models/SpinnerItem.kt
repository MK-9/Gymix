package com.gymix.presentation.book.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SpinnerItem(val id: Int, val title: String) : Parcelable