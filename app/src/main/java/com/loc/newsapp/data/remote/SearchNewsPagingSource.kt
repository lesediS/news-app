package com.loc.newsapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.util.Constants.API_KEY

class SearchNewsPagingSource(
    private val newsApi: NewsApi,
    private val searchQuery: String,
    private val sources: String // This parameter is not currently used; consider removing if unnecessary
) : PagingSource<Int, Article>() {

    private var totalNewsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            // Call the search API
            val newsResponse = newsApi.searchNews(
                searchQuery = searchQuery,
                apiToken = API_KEY, // Pass API token if needed
                locale = "za", // Set locale as needed
                page = page
            )

            // Extract articles from the 'data' field of the response
            val articles = newsResponse.data.map { data ->
                Article(
                    author = null, // Adjust if you have author information in your data model
                    content = "", // Adjust if necessary
                    description = data.description,
                    snippet = data.snippet,
                    publishedAt = data.published_at,
                    source = data.source,
                    title = data.title,
                    url = data.url,
                    urlToImage = data.image_url
                )
            }

            totalNewsCount += articles.size

            // Remove duplicate articles by their title
            val distinctArticles = articles.distinctBy { it.title }

            LoadResult.Page(
                data = distinctArticles,
                nextKey = if (totalNewsCount >= newsResponse.meta.found) null else page + 1,
                prevKey = if (page == 1) null else page - 1 // Allow for previous page navigation
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}