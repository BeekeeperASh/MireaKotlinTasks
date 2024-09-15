package alvshinev.application.mireakotlintasks.data

import alvshinev.application.mireakotlintasks.data.api.NewsApi
import alvshinev.application.mireakotlintasks.data.api.models.ArticleDTO
import alvshinev.application.mireakotlintasks.data.api.models.ResponseDTO
import alvshinev.application.mireakotlintasks.data.database.NewsDatabase
import alvshinev.application.mireakotlintasks.data.database.models.ArticleDBO
import alvshinev.application.mireakotlintasks.data.model.Article
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class ArticlesRepository @Inject constructor(
    private val api: NewsApi,
    private val database: NewsDatabase
) {

    fun getNewsFromApi(query: String): Flow<RequestResult<List<Article>>> {

        val apiRequest =
            flow { emit(api.everything(query = query)) }
                .onEach { result ->
                    if (result.isSuccess) saveArticlesToDb(result.getOrThrow().articles)
                }
                .onEach { result ->
                    if (result.isFailure) {
                        Log.e("Api", "Error getting data from server. Cause = ${result.exceptionOrNull()}")
                    }
                }
                .map { it.toRequestResult() }

        val start = flowOf<RequestResult<ResponseDTO<ArticleDTO>>>(RequestResult.InProgress())
        return merge(apiRequest, start)
            .map { result: RequestResult<ResponseDTO<ArticleDTO>> ->
                result.map { response -> response.articles.map { it.toArticle() } }
            }
    }

    fun getNewsFromDatabase(): Flow<RequestResult<List<Article>>> {

        val dbRequest =
            database.articlesDao()::getAll.asFlow()
                .map<List<ArticleDBO>, RequestResult<List<ArticleDBO>>> { RequestResult.Success(it) }
                .catch {
                    Log.e("Database", "Error: ${it.message}")
                    emit(RequestResult.Error(error = it))
                }

        val start = flowOf<RequestResult<List<ArticleDBO>>>(RequestResult.InProgress())

        return merge(start, dbRequest).map { result ->
            result.map { dbos -> dbos.map { it.toArticle() } }
        }

    }

    private suspend fun saveArticlesToDb(data: List<ArticleDTO>) {
        val dbos = data.map { articleDto -> articleDto.toArticleDbo() }
        database.articlesDao().insert(dbos)
    }

    suspend fun clearNewsDatabase() {
        database.articlesDao().clean()
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
