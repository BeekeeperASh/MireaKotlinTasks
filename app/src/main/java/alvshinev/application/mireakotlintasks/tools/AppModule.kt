package alvshinev.application.mireakotlintasks.tools

import alvshinev.application.mireakotlintasks.BuildConfig
import alvshinev.application.mireakotlintasks.data.api.NewsApi
import alvshinev.application.mireakotlintasks.data.database.NewsDatabase
import android.content.Context
import android.util.Log
import androidx.startup.Initializer
import androidx.work.Configuration
import androidx.work.WorkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule : Initializer<WorkManager> {

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi { // okHttpClient: OkHttpClient?
        return NewsApi(
            baseUrl = BuildConfig.NEWS_API_BASE_URL,
            apiKey = BuildConfig.NEWS_API_KEY,
//            okHttpClient = okHttpClient
        )
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext applicationContext: Context
    ): NewsDatabase {
        return NewsDatabase(applicationContext)
    }

    @Provides
    @Singleton
    override fun create(@ApplicationContext context: Context): WorkManager {
        val configuration = Configuration.Builder().build()
        WorkManager.initialize(context, configuration)
        Log.d("WorkManager", "WorkManager initialized by Hilt this time")
        return WorkManager.getInstance(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }

}
