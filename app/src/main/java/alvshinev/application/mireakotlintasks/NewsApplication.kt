package alvshinev.application.mireakotlintasks

import alvshinev.application.mireakotlintasks.domain.usecases.ClearArticlesFromDatabaseUseCase
import alvshinev.application.mireakotlintasks.domain.usecases.GetArticlesFromApiUseCase
import alvshinev.application.mireakotlintasks.domain.workmanager.NewsUpdateWorker
import android.app.Application
import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import javax.inject.Provider

@HiltAndroidApp
class NewsApplication : Application(), Configuration.Provider {

    @Inject lateinit var workerFactory: NewsWorkerFactory
    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

}

class NewsWorkerFactory @Inject constructor(
    private val getArticlesFromApiUseCase: Provider<GetArticlesFromApiUseCase>,
    private val clearArticlesFromDatabaseUseCase: Provider<ClearArticlesFromDatabaseUseCase>
): WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker = NewsUpdateWorker(
        getArticlesFromApiUseCase,
        clearArticlesFromDatabaseUseCase,
        appContext,
        workerParameters
    )

}
