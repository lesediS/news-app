package com.loc.newsapp.data.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.util.Constants.API_KEY

class NewsPagingSource(
    private val newsApi: NewsApi,
    private val sources: String
) : PagingSource<Int, Article>() {

    private var totalNewsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            // Fetch news from the API
            val newsResponse = newsApi.getNews(page = page)

            // Extract articles from the 'data' field of the response
            val articles = newsResponse.data.map { data ->
                Article(
                    author = null, // Adjust if you have author information in your data model
                    content = "", // Adjust if necessary
                    description = data.description,
                    publishedAt = data.published_at,
                    source = data.source,
                    title = data.title,
                    url = data.url,
                    urlToImage = data.image_url
                )
            }

            LoadResult.Page(
                data = articles,
                nextKey = if (newsResponse.meta.found > (page * newsResponse.meta.limit)) page + 1 else null,
                prevKey = if (page == 1) null else page - 1
            )

            LoadResult.Page(
                data = articles,
                nextKey = if (newsResponse.meta.found > (page * newsResponse.meta.limit)) page + 1 else null,
                prevKey = if (page == 1) null else page - 1 // Allow for previous page navigation
            )
        } catch (e: Exception) {
            Log.e("NewsPagingSource", "Error loading news: ${e.message}", e)
            LoadResult.Error(e)
        }
        /*totalNewsCount += articles.size

        // Remove duplicate articles by their title
        val distinctArticles = articles.distinctBy { it.title }

        LoadResult.Page(
            data = distinctArticles,
            nextKey = if (totalNewsCount >= newsResponse.meta.found) null else page + 1,
            prevKey = if (page == 1) null else page - 1 // Allow for previous page navigation
        )
    } catch (e: Exception) {
        Log.e("NewsPagingSource", "Error loading news: ${e.message}", e)
        LoadResult.Error(e)
    }*/
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}