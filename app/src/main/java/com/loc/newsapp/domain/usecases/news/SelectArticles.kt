package com.loc.newsapp.domain.usecases.news

import com.loc.newsapp.data.local.NewsDAO
import com.loc.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

class SelectArticles (
    private val newsDAO: NewsDAO
) {
    operator fun invoke(): Flow<List<Article>> {
        return newsDAO.getArticles()
    }
}