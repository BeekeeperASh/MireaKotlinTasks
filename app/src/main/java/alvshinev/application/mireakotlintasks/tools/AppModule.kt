package alvshinev.application.mireakotlintasks.tools

import alvshinev.application.mireakotlintasks.BuildConfig
import alvshinev.application.mireakotlintasks.data.api.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi { // okHttpClient: OkHttpClient?
        return NewsApi(
            baseUrl = BuildConfig.NEWS_API_BASE_URL,
            apiKey = BuildConfig.NEWS_API_KEY,
//            okHttpClient = okHttpClient
        )
    }

}
