package com.loc.newsapp.data.remote

import com.loc.newsapp.domain.model.TheNewsApi
import com.loc.newsapp.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top")
    suspend fun getNews(
        @Query("api_token") apiToken: String = API_KEY,
        @Query("locale") locale: String = "za",
        @Query("limit") limit: Int = 3,
        @Query("page") page: Int
    ): TheNewsApi // Update this to use TheNewsApi instead of NewsResponse

    @GET("top")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("api_token") apiToken: String = API_KEY,
        @Query("locale") locale: String = "za",
        @Query("limit") limit: Int = 3,
        @Query("page") page: Int
    ): TheNewsApi // Update this to use TheNewsApi instead of NewsResponse
}