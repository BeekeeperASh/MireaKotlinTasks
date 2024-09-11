package alvshinev.application.mireakotlintasks.data.api

import alvshinev.application.mireakotlintasks.data.api.models.ArticleDTO
import retrofit2.http.GET

interface NewsApi {

    @GET("everything")
    @Suppress("LongParameterList")
    suspend fun everything() : Result<List<ArticleDTO>>

}