package alvshinev.application.mireakotlintasks.data.api

import alvshinev.application.mireakotlintasks.data.api.models.ArticleDTO
import alvshinev.application.mireakotlintasks.data.api.models.Language
import alvshinev.application.mireakotlintasks.data.api.models.ResponseDTO
import alvshinev.application.mireakotlintasks.data.api.models.SortBy
import alvshinev.application.mireakotlintasks.data.api.utils.NewsApiKeyInterceptor
import androidx.annotation.IntRange
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Date

interface NewsApi {

    @GET("everything")
    @Suppress("LongParameterList")
    suspend fun everything(
        @Query("q") query:String? = null,
        @Query("from") from: Date? = null,
        @Query("to") to: Date? = null,
        @Query("languages") languages: List<@JvmSuppressWildcards Language>? = null,
        @Query("sortBy") sortBy: SortBy? = null,
        @Query("pageSize") @IntRange(from = 0, to = 100) pageSize: Int = 100,
        @Query("page") @IntRange(from = 1) page: Int = 1
    ) : Result<ResponseDTO<ArticleDTO>>

}

fun NewsApi(
    baseUrl: String,
    apiKey: String,
    okHttpClient: OkHttpClient? = null,
    json: Json = Json
): NewsApi {
    return retrofit(baseUrl, apiKey, okHttpClient, json).create()
}

private fun retrofit(
    baseUrl: String,
    apiKey: String,
    okHttpClient: OkHttpClient?,
    json: Json
): Retrofit {
    val jsonConverterFactory = json.asConverterFactory("application/json".toMediaType())

    val modifiedOkHttpClient =
        (okHttpClient?.newBuilder() ?: OkHttpClient.Builder()) // okHttpClient?.newBuilder() ?:
            .addInterceptor(NewsApiKeyInterceptor(apiKey))
            .build()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(jsonConverterFactory)
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .client(modifiedOkHttpClient)
        .build()
}
