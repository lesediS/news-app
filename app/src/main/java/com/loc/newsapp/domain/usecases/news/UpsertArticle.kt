package com.loc.newsapp.domain.usecases.news

import com.loc.newsapp.data.local.NewsDAO
import com.loc.newsapp.domain.model.Article

class UpsertArticle(
    private val newsDAO: NewsDAO
) {
    suspend operator fun invoke(article: Article) {
        newsDAO.upsert(article)
    }
}