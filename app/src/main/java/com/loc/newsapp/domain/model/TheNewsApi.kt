package com.loc.newsapp.domain.model

/*data class TheNewsApi(
    val status: String = "ok",
    val totalResults: Int = 0, // You can calculate this based on `Meta.found`
    val articles: List<Article>
)*/

data class TheNewsApi(
    val meta: Meta,
    val data: List<Data>
)