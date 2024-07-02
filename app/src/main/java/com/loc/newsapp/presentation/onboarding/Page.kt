package com.loc.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.loc.newsapp.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Dummy title",
        description = "Dummy description for this page1.",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Dummy val title",
        description = "Dummy description for this page2.",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Dummy val title",
        description = "Dummy description for this page3.",
        image = R.drawable.onboarding3
    )
)
