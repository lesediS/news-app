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
        title = "Latest news",
        description = "Get the latest scoops from across the globe.",
        image = R.drawable.latest_news
    ),
    Page(
        title = "Always relevant",
        description = "Find news relevant to you in just a few clicks.",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Save for later",
        description = "Save articles you may want to read later wherever, whenever.",
        image = R.drawable.international
    )
)
