package com.loc.newsapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Meta(
    val found: Int,
    val limit: Int,
    val page: Int,
    val returned: Int
): Parcelable