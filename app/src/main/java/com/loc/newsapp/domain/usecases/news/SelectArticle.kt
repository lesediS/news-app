package com.loc.newsapp.domain.usecases.news

import com.loc.newsapp.data.local.NewsDAO
import com.loc.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow


class SelectArticle (
    private val newsDAO: NewsDAO
) {
    suspend operator fun invoke(url: String): Article? {
        return newsDAO.getArticle(url)
    }
}

