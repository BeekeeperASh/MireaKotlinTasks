package alvshinev.application.mireakotlintasks.data

import alvshinev.application.mireakotlintasks.data.api.NewsApi
import alvshinev.application.mireakotlintasks.data.api.models.ArticleDTO
import alvshinev.application.mireakotlintasks.data.api.models.ResponseDTO
import alvshinev.application.mireakotlintasks.data.model.Article
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class ArticlesRepository @Inject constructor(
    private val api: NewsApi
) {

    fun getNewsFromApi(query: String): Flow<RequestResult<List<Article>>> {

        val apiRequest =
            flow { emit(api.everything(query = query)) }
                .onEach { result ->
                    if (result.isFailure) {
                        Log.e("Error", "Error getting data from server. Cause = ${result.exceptionOrNull()}")
                    }
                }
                .map { it.toRequestResult() }

        val start = flowOf<RequestResult<ResponseDTO<ArticleDTO>>>(RequestResult.InProgress())
        return merge(apiRequest, start)
            .map { result: RequestResult<ResponseDTO<ArticleDTO>> ->
                result.map { response -> response.articles.map { it.toArticle() } }
            }
    }

    fun getArticles(): List<Article> {
        return listOf(
            Article(
                title = "News about Android",
                description = "Android Android Android",
                author = null,
                source = null,
                url = null,
                urlToImage = null,
                publishedAt = null,
                content = null
            ),
            Article(
                title = "News about Android",
                description = "Android Android Android",
                author = null,
                source = null,
                url = null,
                urlToImage = null,
                publishedAt = null,
                content = null
            ),
            Article(
                title = "News about Android",
                description = "Android Android Android",
                author = null,
                source = null,
                url = null,
                urlToImage = null,
                publishedAt = null,
                content = null
            ),
            Article(
                title = "News about Android",
                description = "Android Android Android",
                author = null,
                source = null,
                url = null,
                urlToImage = null,
                publishedAt = null,
                content = null
            ),
            Article(
                title = "News about Android",
                description = "Android Android Android",
                author = null,
                source = null,
                url = null,
                urlToImage = null,
                publishedAt = null,
                content = null
            ),
            Article(
                title = "News about Android",
                description = "Android Android Android",
                author = null,
                source = null,
                url = null,
                urlToImage = null,
                publishedAt = null,
                content = null
            ),
        )
    }

}
