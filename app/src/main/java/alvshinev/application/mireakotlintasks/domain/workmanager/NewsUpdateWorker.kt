package alvshinev.application.mireakotlintasks.domain.workmanager

import alvshinev.application.mireakotlintasks.domain.usecases.ClearArticlesFromDatabaseUseCase
import alvshinev.application.mireakotlintasks.domain.usecases.GetArticlesFromApiUseCase
import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import javax.inject.Inject
import javax.inject.Provider

@HiltWorker
class NewsUpdateWorker @AssistedInject constructor (
    @Assisted private val getArticlesFromApiUseCase: Provider<GetArticlesFromApiUseCase>,
    @Assisted private val clearArticlesFromDatabaseUseCase: Provider<ClearArticlesFromDatabaseUseCase>,
    @Assisted ctx: Context,
    @Assisted params: WorkerParameters,
): CoroutineWorker(ctx, params) {
    override suspend fun doWork(): Result {

        return try {

            Log.d("WorkManager", "Работа началась")

            fetchLatestNews(getQueryFromPrefs(applicationContext))

            Log.d("WorkManager", "Работа завершилась успешно")

            Result.success()
        } catch (exception: Exception) {

            Result.failure()
        }
    }

    private suspend fun fetchLatestNews(query: String) {
        clearArticlesFromDatabaseUseCase.get().invoke()
        getArticlesFromApiUseCase.get().invoke(query).collect{}
    }

    private fun getQueryFromPrefs(context: Context) : String {
        val prefs = context.getSharedPreferences("request_shared_preferences", Context.MODE_PRIVATE)
        return prefs.getString("last_request", "android") ?: "news"
    }
}

